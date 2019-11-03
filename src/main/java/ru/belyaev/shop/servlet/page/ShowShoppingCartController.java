// Created by Vologda developer.
// Date: 15.10.2019
// Time: 8:34

package ru.belyaev.shop.servlet.page;

import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping-cart")
public class ShowShoppingCartController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtil.forwardToPage("shopping-cart.jsp",req,resp);
    }
}
