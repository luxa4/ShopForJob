//// Created by Vologda developer.
//// Date: 12.11.2019
//// Time: 20:22
//
//package ru.belyaev.shop.servlet.page;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ru.belyaev.shop.Constants;
//import ru.belyaev.shop.service.SocialService;
//import ru.belyaev.shop.servlet.AbstractController;
//import ru.belyaev.shop.util.RoutingUtil;
//import ru.belyaev.shop.util.SessionUtil;
//import ru.belyaev.shop.util.WebUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//public class SingInController  extends AbstractController {
//    private static final long serialVersionUID = -7565542222448593595L;
//
//    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (SessionUtil.isCurrentAccountCreated(req)) {
//            RoutingUtil.redirect("/my-orders", req, resp);
//        } else {
//            RoutingUtil.forwardToPage("sign-in.jsp", req, resp);
//        }
//    }
//
//
//    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (SessionUtil.isCurrentAccountCreated(req)) {
//            RoutingUtil.redirect("/my-orders", req, resp);
//        } else {
//            String targetUrl = req.getParameter("target");
//            req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, targetUrl);
//            System.out.println("Атрибут SUCCESS_REDIRECT_URL_AFTER_SIGNIN равен - "+req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN));
//            RoutingUtil.redirect(socialService.getAuthorizeUrl(), req, resp);
//        }
//    }
//}
