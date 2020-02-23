// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:07

package ru.belyaev.shop.entity;


import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem() {
        super();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
                "idOrder=" + order +
                ", product=" + product +
                ",  count=" + count +
                ", id=" + id +
                '}';
    }
}
