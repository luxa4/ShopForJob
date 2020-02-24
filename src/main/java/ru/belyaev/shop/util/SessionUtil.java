package ru.belyaev.shop.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;


public class SessionUtil {
	public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
		ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			setCurrentShoppingCart(req, shoppingCart);
		}
		return shoppingCart;
	}

	public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
		return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
	}

	public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
		req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
	}

	public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
	}

	public static Cookie findShoppingCartCookie(HttpServletRequest req) {
		return WebUtils.findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
	}

	public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
				Constants.Cookie.SHOPPING_CART.getTtl(), resp);

	}

	public static Account getCurrentAccount(HttpServletRequest req) {
		return (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
    }

    public static void setCurrentAccount(HttpSession httpSession, Account currentAccount) {
		httpSession.setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
    }

    public static boolean isCurrentAccountCreated(HttpServletRequest req) {
		if (req.getSession().getAttribute(Constants.CURRENT_ACCOUNT) != null) {
			return true;
		} else {
			return false;
		}
    }

	private SessionUtil() {
	}
}
