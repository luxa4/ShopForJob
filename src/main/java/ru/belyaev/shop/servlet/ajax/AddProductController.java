// Created by Vologda developer.
// Date: 03.11.2019
// Time: 17:53

package ru.belyaev.shop.servlet.ajax;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.servlet.page.AllProductController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@SessionAttributes({"CATEGORY_LIST", "PRODUCER_LIST"})
public class AddProductController extends AbstractController {
    private static final long serialVersionUID = 5023867691534917359L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddProductController.class);

    @RequestMapping(value = "/ajax/json/product/add", method = RequestMethod.POST)
    public void addProductToCart(HttpServletRequest req, HttpServletResponse resp,
                                 @RequestParam(name = "idProduct") String idProduct,
                                 @RequestParam(name = "count") String count) throws  IOException {
        LOGGER.info("-->>> Launching {}", AddProductController.class.getSimpleName());
        ProductForm productForm = createProductForm(idProduct, count); // Get Product
        ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req); // Get ShoppingCart
        orderService.addProductToShoppingCart(productForm, shoppingCart); // Add product in Cart

        // Для того, чтобы вернуть в - response - totalCount and totalCost - используем формат JSON
        JSONObject r = new JSONObject();
        r.put("totalCount", shoppingCart.getTotalCount());
        r.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtil.sendJSON(r , req, resp);

    }
}
