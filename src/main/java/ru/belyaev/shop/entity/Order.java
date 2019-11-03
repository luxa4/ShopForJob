// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:09

package ru.belyaev.shop.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Long> {
    private Integer idAccount;
    private List<OrderItem> products;
    private Timestamp created;
    private int totalCount;

    public int getTotalCount(List<OrderItem> products) {
        totalCount = 0;
        if (products != null) {
            for (OrderItem item : products) {
                totalCount += item.getCount();
            }
            return totalCount;
        }

        return totalCount;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idAccount=" + idAccount +
                ", products=" + products +
                ", created=" + created +
                ", totalCount=" + totalCount +
                ", id=" + id +
                '}';
    }
}
