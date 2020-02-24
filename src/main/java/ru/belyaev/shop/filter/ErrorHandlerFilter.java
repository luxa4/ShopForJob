package ru.belyaev.shop.filter;

import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.belyaev.shop.exception.AbstractErrorException;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.exception.ResourceNotFoundException;
import ru.belyaev.shop.exception.ValidationException;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.UrlUtils;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


@Component
@Order(1)
public class ErrorHandlerFilter extends AbstractFilter {

	private final static String INTERNAL_ERROR = "INTERNAL_ERROR";

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(req, new ThrowExceptionInsteadInSendErrorResponse(resp));
		} catch (Throwable th) {
			String requestUrl = req.getRequestURI();
			if (th instanceof ValidationException) {
				LOGGER.warn("Request is not valid" + th.getMessage());
			} else {
				LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
			}

			handleException(requestUrl, th, req, resp);

		}
	}

	private int getStatusCode (Throwable th) {
		if (th instanceof AbstractErrorException) {
			return ((AbstractErrorException) th).getCode();
		} else {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}

	// method, which will be process code
	private void handleException (String requestUrl, Throwable th, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int statusCode = getStatusCode(th);
		resp.setStatus(statusCode);

//		if (UrlUtils.isAjaxJsonUrl(requestUrl)) {
//			JSONObject json = new JSONObject();
//			json.put("message", th instanceof ValidationException ? th.getMessage() : INTERNAL_ERROR);
//			RoutingUtil.sendJSON(json, req, resp);
//		} else if (UrlUtils.isAjaxHtmlUrl(requestUrl)) {
//			RoutingUtil.sendHTMLFragment(INTERNAL_ERROR, req,resp);
//		} else {
			req.setAttribute("statusCode", statusCode);
			RoutingUtil.forwardToPage("error.jsp", req, resp);
//		}
	}


//	Для отображения своего вида стандартных ошибок создаем класс
	private static class ThrowExceptionInsteadInSendErrorResponse extends HttpServletResponseWrapper {

		public ThrowExceptionInsteadInSendErrorResponse(HttpServletResponse response) {
		super(response);
		}

	// переопределяем методы ответа сервера

	@Override
	public void sendError(int sc) throws IOException {
		sendError(sc, INTERNAL_ERROR); // вызывает метод ниже. Второй парамет только для метода - default -
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {

		switch (sc) {
			case 404: {
				throw new ResourceNotFoundException(msg);
			}
			case 400: {
				throw new ValidationException(msg);
			}
			case 403: {
				throw new AccessDeniedException(msg);
			}

			default: {
				throw new InternalServerErrorException(msg);
			}

		}
	}
}



}
