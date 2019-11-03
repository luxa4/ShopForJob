package ru.belyaev.shop.model;

import ru.belyaev.shop.Constants;
import ru.belyaev.shop.exception.ValidationException;


import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private int totalCount=0;
    Map<Integer,ShoppingCartItem> products = new HashMap<Integer,ShoppingCartItem>();

    public void addProduct(int idProduct, int count) {
        validateShoppingCartSize();
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem == null) {
            shoppingCartItem = new ShoppingCartItem(idProduct, count);
            products.put(idProduct, shoppingCartItem);
        } else {
            validateProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(count+shoppingCartItem.getCount());
        }

    }

    public void removeProduct (int idProduct, int count) {
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > count) {
                shoppingCartItem.setCount(shoppingCartItem.getCount()-count);
            } else {
                products.remove(idProduct);
            }
        }
        refreshStatistic();
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }

    public void refreshStatistic() {
            totalCount = 0 ;
            for (ShoppingCartItem shoppingCartItem: products.values()) {
                totalCount += shoppingCartItem.getCount();
            }
    }

    public void validateShoppingCartSize () {
        if (products.size() > Constants.MAX_PRODUCTS_COUNT_IN_CART)
            throw new ValidationException("Превышен максимальный размер корзины - 20 товаров");

    }

    public void validateProductCount (int count)  {
        if (count > Constants.MAX_ONE_PRODUCT_COUNT_IN_CART)
            throw new ValidationException("Вы полождили одного товара больше 10 шт");

    }

    public int getTotalCount() {
        return totalCount;
    }
}
