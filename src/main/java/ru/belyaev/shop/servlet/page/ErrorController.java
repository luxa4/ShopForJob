package ru.belyaev.shop.servlet.page;

import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statusCode",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        RoutingUtil.forwardToPage("error.jsp",req,resp);
    }


}
