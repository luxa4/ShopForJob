/*
 * Created by Vologda Developer
 * Date: 11.01.2020
 * Time: 0:55
 */


package ru.belyaev.shop.servlet.ajax;

import org.json.JSONObject;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractProductController extends AbstractController {

    private static final long serialVersionUID = -2428231199680000991L;


    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm form = createProductForm(req);
        ShoppingCart shoppingCart = getCurrentShoppingCart(req);
        processProductForm(form, shoppingCart, req, resp);
        if(!SessionUtil.isCurrentShoppingCartCreated(req)) {
            SessionUtil.setCurrentShoppingCart(req, shoppingCart);
        }
        sendResponse(shoppingCart, req, resp);
    }

    private ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
        ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req);
        if(shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    protected abstract void processProductForm(ProductForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    protected void sendResponse(ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        cardStatistics.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtil.sendJSON(cardStatistics, req, resp);
    }

}
