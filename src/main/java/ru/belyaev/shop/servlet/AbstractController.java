package ru.belyaev.shop.servlet;

import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;
import ru.belyaev.shop.service.SocialService;
import ru.belyaev.shop.service.impl.ServiceManager;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    private ProductService productService;
    private OrderService orderService;
    private SocialService socialService;


    @Override
    public void init()  {
            productService = ServiceManager.getInstance(getServletContext()).getProductService();
            orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
            socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
    }

    public ProductService getProductService() {
        return productService;
    }
    public OrderService getOrderService() {
        return orderService;
    }

    public SocialService getSocialService() {
        return socialService;
    }

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
