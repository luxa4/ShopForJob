package ru.belyaev.shop.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;
import ru.belyaev.shop.service.SocialService;
import ru.belyaev.shop.service.impl.ServiceManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class AbstractController {

    @Autowired
    public ProductService productService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public SocialService socialService;


    public final SearchForm createSearchForm(HttpServletRequest req){
        return new SearchForm(
                req.getParameter("query"),
                req.getParameterValues("category"),
                req.getParameterValues("producer"));
    }

    public static int pageCount (int countProduct, int limit) {
        int rs = countProduct/limit;
        if (rs*limit != countProduct) {
            return rs++;
        } else {
            return rs;
        }
    }

    public static int getPage(HttpServletRequest req) {
        try {
            return Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public final ProductForm createProductForm(HttpServletRequest req){
        return new ProductForm(
                Integer.parseInt(req.getParameter("idProduct")),
                Integer.parseInt(req.getParameter("count"))
        );

    }



}
