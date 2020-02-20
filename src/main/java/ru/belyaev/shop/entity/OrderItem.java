// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:07

package ru.belyaev.shop.entity;


import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order idOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "count")
    private Integer count;

    public OrderItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public OrderItem() {
        super();
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrder=" + idOrder +
                ", product=" + product +
                ",  count=" + count +
                ", id=" + id +
                '}';
    }
}
