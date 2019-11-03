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

@WebServlet("/products")
public class AllProductController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Сработал контролер products");
        List<Product> products = getProductService().listAllProduct(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        int countProduct = getProductService().countAllProducts();
        req.setAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        RoutingUtil.forwardToPage("products.jsp",req,resp);
    }
}
