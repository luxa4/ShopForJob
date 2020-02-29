package ru.belyaev.shop.util;

import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public final class RoutingUtil {
	
	public static void forwardToFragment(String jspFragment, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(req, resp);
	}

	public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("CURRENT_PAGE", "/WEB-INF/JSP/page/" + jspPage);
		req.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(req, resp);
	}

	public static void sendHTMLFragment(String text, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println(text);
		resp.getWriter().close();
	}

	public static void redirect(String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(url);
	}

	public static void sendJSON (JSONObject json, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().println(json.toString());
        resp.getWriter().close();
	}

	public static ModelAndView forwardToPageMVC(Model model, String page) {
		model.addAttribute("CURRENT_PAGE", "/WEB-INF/JSP/page/" + page);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page-template");
		return modelAndView;
	}

}
