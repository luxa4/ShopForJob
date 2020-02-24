// Created by Vologda developer.
// Date: 16.11.2019
// Time: 12:03

package ru.belyaev.shop.servlet.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MyOrdersController extends AbstractController {
    private static final long serialVersionUID = -3940675839945296526L;

    @RequestMapping(value = "/my-orders", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account currentAccount = SessionUtil.getCurrentAccount(req);
        List<Order> orders = orderService.listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orders);
        int orderCount = orderService.countMyOrders(currentAccount);
        req.setAttribute("pageCount", pageCount(orderCount, Constants.ORDERS_PER_PAGE));
        RoutingUtil.forwardToPage("my-orders.jsp", req, resp);
    }
}

