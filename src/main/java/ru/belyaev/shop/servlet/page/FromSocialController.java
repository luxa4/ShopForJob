// Created by Vologda developer.
// Date: 12.11.2019
// Time: 20:10

package ru.belyaev.shop.servlet.page;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class FromSocialController extends AbstractController {
    private static final long serialVersionUID = -2818067285503260741L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FromSocialController.class);

    @RequestMapping(value = "/from-social", method = RequestMethod.GET)
    protected RedirectView fromSocialWebSite(HttpSession httpSession, @RequestParam(name = "code", required = false) String code) throws IOException {
        LOGGER.info("-->>> Get data from FaceBook and return back ");
        if (code != null) {
            LOGGER.info("-->>> Data from Facebook is valid");
            SocialAccount socialAccount = socialService.getSocialAccount(code);
            Account currentAccount = orderService.authenticate(socialAccount);
            SessionUtil.setCurrentAccount(httpSession, currentAccount);
            return redirectSuccessPage(httpSession);
        } else {
            LOGGER.info("-->>> Data from Facebook incorrect or empty");
            return new RedirectView("/sign-in");
        }
    }

    protected RedirectView redirectSuccessPage (HttpSession httpSession) throws IOException {
        String targetUrl = (String) httpSession.getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
        if (targetUrl != null) {
            httpSession.removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            return new RedirectView(URLDecoder.decode(targetUrl, "UTF-8"));
        } else {
            return new RedirectView("/my-order");
        }

    }
}
