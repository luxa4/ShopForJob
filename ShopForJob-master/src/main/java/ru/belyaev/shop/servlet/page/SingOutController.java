// Created by Vologda developer.
// Date: 12.11.2019
// Time: 20:22

package ru.belyaev.shop.servlet.page;

import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-out")
public class SingOutController extends AbstractController {
    private static final long serialVersionUID = 5455146826211606109L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        RoutingUtil.redirect("/products", req, resp);
    }
}
