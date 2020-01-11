/*
 * Created by Vologda Developer
 * Date: 11.01.2020
 * Time: 0:45
 */


package ru.belyaev.shop.servlet.ajax;

import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/product/remove")
public class RemoveProductController extends AbstractProductController {
    private static final long serialVersionUID = -3046216247699203961L;

    @Override
    protected void processProductForm(ProductForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getOrderService().removeProductFromShoppingCart(form, shoppingCart);
        if (shoppingCart.getItems().isEmpty()) {
            SessionUtil.clearCurrentShoppingCart(req, resp);
        } else {
            String cookieValue = getOrderService().serializeShoppingCart(shoppingCart);
            SessionUtil.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
    }
}
