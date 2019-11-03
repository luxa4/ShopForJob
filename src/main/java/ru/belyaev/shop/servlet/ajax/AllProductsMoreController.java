package ru.belyaev.shop.servlet.ajax;

import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtil.forwardToFragment("products-list.jsp",req,resp);
    }
}
