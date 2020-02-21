// Created by Vologda developer.
// Date: 15.10.2019
// Time: 8:34

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShowShoppingCartController extends AbstractController {

    private static final long serialVersionUID = 2057475736109395575L;

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Verify if SoppingCart is null, we redirect to /products
        if (SessionUtil.isCurrentShoppingCartCreated(req)) {
            RoutingUtil.forwardToPage("shopping-cart.jsp", req, resp);
        } else {
            RoutingUtil.redirect("/products", req, resp);
        }
    }
}
