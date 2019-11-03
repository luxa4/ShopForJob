// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:05

package ru.belyaev.shop.servlet.page;

import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController {
    private static final long serialVersionUID = 7275592594451636455L;
    private static final int SUBSTRING_INDEX = "/products".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = getProductService().listProductsByCategory(categoryUrl, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        int countProduct = getProductService().countAllProductByCategory(categoryUrl);
        req.setAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        req.setAttribute("products", products);
        req.setAttribute("selectedCategoryUrl",categoryUrl); // для того, чтобы подсветить выбранную категорию в списке ASIDE
        RoutingUtil.forwardToPage("products.jsp",req,resp);
    }
}
