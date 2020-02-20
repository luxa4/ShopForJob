package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
public class AllProductController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllProductController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    protected void showMainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = createSearchForm(req);
        LOGGER.info("-->>> Launching {}", AllProductController.class.getSimpleName());
        List<Product> products = productService.listAllProduct(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        int countProduct = productService.countAllProducts();
        req.setAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));

        RoutingUtil.forwardToPage("products.jsp",req,resp);
    }
}
