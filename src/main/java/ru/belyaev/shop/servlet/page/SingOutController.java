//// Created by Vologda developer.
//// Date: 12.11.2019
//// Time: 20:22
//
//package ru.belyaev.shop.servlet.page;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ru.belyaev.shop.servlet.AbstractController;
//import ru.belyaev.shop.util.RoutingUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//public class SingOutController extends AbstractController {
//    private static final long serialVersionUID = 5455146826211606109L;
//
//    @RequestMapping(value = "/sign-out", method = RequestMethod.POST)
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().invalidate();
//        RoutingUtil.redirect("/products", req, resp);
//    }
//}
