package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.belyaev.shop.Constants;

import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.service.impl.ServiceManager;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@Controller
public class AllProductController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllProductController.class);

    @Autowired
    ServiceManager serviceManager;



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showAllPerson(Model model,  HttpSession httpSession ) {
        httpSession.setAttribute("CATEGORY_LIST", serviceManager.productService.listAllCategories());
        httpSession.setAttribute("PRODUCER_LIST", serviceManager.productService.listAllProducers());
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

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        SearchForm searchForm = createSearchForm(req);
//        LOGGER.info("-->>> Launching {}", AllProductController.class.getSimpleName());
//        List<Product> products = productService.listAllProduct(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
//        req.setAttribute("products", products);
//        int countProduct = productService.countAllProducts();
//        req.setAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));
//
//        RoutingUtil.forwardToPage("products.jsp",req,resp);
//    }
}
