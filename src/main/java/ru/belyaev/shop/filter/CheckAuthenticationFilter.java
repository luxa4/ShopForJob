// Created by Vologda developer.
// Date: 12.11.2019
// Time: 19:56

package ru.belyaev.shop.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;
import ru.belyaev.shop.util.UrlUtils;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// The Filter verify only page ("/my-orders"). Фильр проверяет Зарегистрирован USER или нет
@Component
@Order(5)
public class CheckAuthenticationFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SessionUtil.isCurrentAccountCreated(request)) {

            chain.doFilter(request,response);
        } else {
            String requestURI = request.getRequestURI();
            if (UrlUtils.isAjaxUrl(requestURI)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("401");
            } else {
                request.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, requestURI);
                RoutingUtil.redirect("/sign-in", request, response);
            }
        }
    }
}
