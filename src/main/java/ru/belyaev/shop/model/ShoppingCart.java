package ru.belyaev.shop.model;

import org.springframework.stereotype.Component;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.ValidationException;


import java.math.BigDecimal;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShoppingCart {
    private int totalCount=0;
    private BigDecimal totalCost = BigDecimal.ZERO;
    Map<Integer,ShoppingCartItem> products = new HashMap<Integer,ShoppingCartItem>();

    public void addProduct(Product product, int count) {
        validateShoppingCartSize();
        ShoppingCartItem shoppingCartItem = products.get(product.getId());
        if (shoppingCartItem == null) {
            shoppingCartItem = new ShoppingCartItem(product, count);
            products.put(product.getId(), shoppingCartItem);
        } else {
            validateProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(count+shoppingCartItem.getCount());
        }
        refreshStatistic();

    }


    public BigDecimal getTotalCost() {
        return totalCost;
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
        totalCount = 0;
        totalCost = BigDecimal.ZERO;

            for (ShoppingCartItem shoppingCartItem: products.values()) {
                totalCount += shoppingCartItem.getCount();
                totalCost = totalCost.add(shoppingCartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
            }
    }

    public void validateShoppingCartSize () {
        if (products.size() > Constants.MAX_PRODUCTS_COUNT_IN_CART)
            throw new ValidationException("Превышен максимальный размер корзины - 20 товаров");

    }

    public void validateProductCount (int count)  {
        if (count > Constants.MAX_ONE_PRODUCT_COUNT_IN_CART)
            throw new ValidationException("Вы положили одного товара больше 10 шт");

    }

    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "totalCount=" + totalCount +
                ", totalCost=" + totalCost +
                ", products=" + products +
                '}';
    }
}
