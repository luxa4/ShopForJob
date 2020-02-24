package ru.belyaev.shop.service;

import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.SocialAccount;

import java.util.List;

public interface OrderService {

    long makeOrder(ShoppingCart shoppingCart, Account currentAccount);

    Order findOrderById(Long id, Account currentAccount);

    List<Order> listMyOrders(Account currentAccount, int page, int limit);

    int countMyOrders(Account currentAccount);

    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    Account authenticate(SocialAccount socialAccount);

    String serializeShoppingCart(ShoppingCart shoppingCart);
}
