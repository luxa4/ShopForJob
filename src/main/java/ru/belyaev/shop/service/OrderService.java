package ru.belyaev.shop.service;

import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;

public interface OrderService {


    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);


}
