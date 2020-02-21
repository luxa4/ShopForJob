//// Created by Vologda developer.
//// Date: 12.11.2019
//// Time: 20:27
//
//package ru.belyaev.shop.filter;
//
//import ru.belyaev.shop.Constants;
//import ru.belyaev.shop.util.WebUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//// Control all pages
//@WebFilter(filterName = "SetCurrentRequestUrlFilter")
//public class SetCurrentRequestUrlFilter extends AbstractFilter {
//    @Override
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setAttribute(Constants.CURRENT_REQUEST_URL, WebUtils.getCurrentRequestUrl(request));
//        chain.doFilter(request, response);
//    }
//}
