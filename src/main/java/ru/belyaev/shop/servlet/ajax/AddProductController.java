// Created by Vologda developer.
// Date: 03.11.2019
// Time: 17:53

package ru.belyaev.shop.servlet.ajax;

import org.json.JSONObject;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/products/add")
public class AddProductController extends AbstractController {
    private static final long serialVersionUID = 5023867691534917359L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm productForm = createProductForm(req); // Get ProductForm
        ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req); // Get ShoppingCart
        getOrderService().addProductToShoppingCart(productForm, shoppingCart); // Add product in Cart

        // Для того, чтобы вернуть в - response - totalCount and totalCost - используем формат JSON
        JSONObject r = new JSONObject();
        r.put("totalCount", shoppingCart.getTotalCount());
        r.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtil.sendJSON(r,req, resp);

    }
}
