// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:05

package ru.belyaev.shop.entity;


import ru.belyaev.framework.annotationJDBC.Column;

public class Producer extends AbstractEntity<Integer> {
    private String name;
    @Column("product_count")
    private Integer productCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
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
