// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:09

package ru.belyaev.shop.entity;


import ru.belyaev.framework.annotationJDBC.Column;
import ru.belyaev.framework.annotationJDBC.Transient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Long> {
    @Column("id_account")
    private Integer idAccount;
    @Transient
    private List<OrderItem> products;
    private Timestamp created;
    @Transient
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

    public BigDecimal getTotalCost() {
        BigDecimal cost = BigDecimal.ZERO;
        if (products != null) {
            for (OrderItem item : products) {
                cost = cost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            }
        }
        return cost;
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
