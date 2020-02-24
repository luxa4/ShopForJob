package ru.belyaev.shop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.ShoppingCartItem;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.impl.ServiceManager;
import ru.belyaev.shop.util.SessionUtil;

@Component
@Order(4)
public class AutoRestoreShoppingCartFilter extends AbstractFilter {

	@Autowired
    private OrderService orderService;



    private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if(req.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null){
			if(!SessionUtil.isCurrentShoppingCartCreated(req)) {
				Cookie cookie = SessionUtil.findShoppingCartCookie(req);
				if(cookie != null) {
					ShoppingCart shoppingCart = shoppingCartFromString(cookie.getValue());
					SessionUtil.setCurrentShoppingCart(req, shoppingCart);
				}
			}
			req.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
		}

		chain.doFilter(req, resp);
	}

	protected ShoppingCart shoppingCartFromString(String cookieValue) {
		ShoppingCart shoppingCart = new ShoppingCart();
		String[] items = cookieValue.split("\\|");
		for (String item : items) {
			String data[] = item.split("-");
			try {
				int idProduct = Integer.parseInt(data[0]);
				int count = Integer.parseInt(data[1]);
				orderService.addProductToShoppingCart(new ProductForm(idProduct,count),shoppingCart);

			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return shoppingCart;
	}

//	protected String shoppingCartToString(ShoppingCart shoppingCart) {
//		StringBuilder res = new StringBuilder();
//		for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
//			res.append(shoppingCartItem.getIdProduct()).append("-").append(shoppingCartItem.getCount()).append("|");
//		}
//		if (res.length() > 0) {
//			res.deleteCharAt(res.length() - 1);
//		}
//		return res.toString();
//	}

	/*
ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req);
			String cookieValue = shoppingCartToString(shoppingCart);
			SessionUtil.updateCurrentShoppingCartCookie(cookieValue, resp);
	 */
}
