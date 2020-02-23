// Created by Vologda developer.
// Date: 12.11.2019
// Time: 20:10

package ru.belyaev.shop.servlet.page;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class FromSocialController extends AbstractController {
    private static final long serialVersionUID = -2818067285503260741L;

    @RequestMapping(value = "/from-social", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            SocialAccount socialAccount = socialService.getSocialAccount(code);
            CurrentAccount currentAccount = orderService.authenticate(socialAccount);
            req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
            redirectSuccessPage(req,resp);
        } else {
            RoutingUtil.redirect("/sing-in",req, resp);
        }
    }

    protected void redirectSuccessPage (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
        if (targetUrl != null) {
            req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            RoutingUtil.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp);
        } else {
            RoutingUtil.redirect("/my-order", req , resp);
        }

    }
}
