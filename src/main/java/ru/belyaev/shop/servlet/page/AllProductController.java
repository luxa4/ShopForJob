package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.service.impl.ServiceManager;
import ru.belyaev.shop.servlet.AbstractController;
import java.util.List;

@Controller
public class AllProductController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllProductController.class);

    @Autowired
    ServiceManager serviceManager;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showAllPerson(Model model) {
        List<Product> products = productService.listAllProduct(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("products", products);
        int countProduct = productService.countAllProducts();
        model.addAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));


        modelAndView.setViewName("page-template");
        model.addAttribute("CURRENT_PAGE", "/WEB-INF/JSP/page/products.jsp");
        LOGGER.info("-->>> Launching {}", AllProductController.class.getSimpleName());
        return modelAndView;
    }

}
