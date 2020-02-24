//// Created by Vologda developer.
//// Date: 20.10.2019
//// Time: 17:16
//
//package ru.belyaev.shop.servlet.ajax;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ru.belyaev.shop.Constants;
//import ru.belyaev.shop.entity.Product;
//import ru.belyaev.shop.form.SearchForm;
//import ru.belyaev.shop.servlet.AbstractController;
//import ru.belyaev.shop.util.RoutingUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//public class SearchResultMoreController extends AbstractController {
//    private static final long serialVersionUID = 6725071099705912476L;
//
//    @RequestMapping(value = "/ajax/html/more/search", method = RequestMethod.GET)
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        SearchForm searchForm = createSearchForm(req);
//        List<Product> products = productService.ListProductBySearchForm(searchForm, getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE);
//        req.setAttribute("products", products);
//        RoutingUtil.forwardToFragment("product-list.jsp", req, resp);
//    }
//}
