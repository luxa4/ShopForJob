// Created by Vologda developer.
// Date: 16.11.2019
// Time: 12:03

package ru.belyaev.shop.servlet.page;

import ru.belyaev.shop.servlet.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my-orders")
public class MyOrdersController extends AbstractController {
    private static final long serialVersionUID = -3940675839945296526L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("Вы на странице через метод My-orders ");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
