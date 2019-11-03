package ru.belyaev.shop.servlet;

import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;
import ru.belyaev.shop.service.impl.ServiceManager;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AbstractController extends HttpServlet {

    private ProductService productService;
    private OrderService orderService;


    @Override
    public void init()  {

            System.out.println("Зашли в init AbstractController");
            productService = ServiceManager.getInstance(getServletContext()).getProductService();

            orderService = ServiceManager.getInstance(getServletContext()).getOrderService();

    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public final SearchForm createSeachForm(HttpServletRequest req){
        return new SearchForm(req.getParameter("query"),req.getParameterValues("category"),req.getParameterValues("producer"));
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


}
