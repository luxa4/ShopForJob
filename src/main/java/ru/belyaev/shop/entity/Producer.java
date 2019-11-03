// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:05

package ru.belyaev.shop.entity;

public class Producer extends AbstractEntity<Integer> {
    private String name;
    private int productCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                ", id=" + id +
                '}';
    }
}
