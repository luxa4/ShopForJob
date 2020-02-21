package ru.belyaev.shop.model;

import org.springframework.stereotype.Component;
import ru.belyaev.shop.entity.Product;

@Component
public class ShoppingCartItem {
    private Product product;
    private int count;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
