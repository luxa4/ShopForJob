// Created by Vologda developer.
// Date: 12.11.2019
// Time: 20:22

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import ru.belyaev.shop.servlet.AbstractController;
import javax.servlet.http.HttpSession;


@Controller
public class SingOutController extends AbstractController {
    private static final long serialVersionUID = 5455146826211606109L;

    @RequestMapping(value = "/sign-out", method = RequestMethod.POST)
    protected RedirectView signOut (HttpSession httpSession) {
        httpSession.invalidate();
        return new RedirectView("/products");
    }
}
