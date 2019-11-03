// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:08

package ru.belyaev.shop.servlet.page;

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

@WebServlet("/search")
public class SearchController extends AbstractController {
    private static final long serialVersionUID = 509924737698931116L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = createSeachForm(req);
        int countProduct = getProductService().countProductBySearchFrom(searchForm);
        List<Product> products = getProductService().ListProductBySearchForm(searchForm, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        req.setAttribute("products", products);
        req.setAttribute("searchForm", searchForm);
        req.setAttribute("productCount", countProduct); // quantity product that was found
        RoutingUtil.forwardToPage("search-result.jsp", req, resp);
    }
}
