package ru.belyaev.shop.service;

import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.SocialAccount;

public interface OrderService {


    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

//    void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    CurrentAccount authenticate(SocialAccount socialAccount);

    long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);

    Order findOrderById(Long id, CurrentAccount currentAccount);


}
