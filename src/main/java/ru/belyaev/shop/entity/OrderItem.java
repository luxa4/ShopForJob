// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:07

package ru.belyaev.shop.entity;


import ru.belyaev.framework.annotationJDBC.Child;
import ru.belyaev.framework.annotationJDBC.Column;

public class OrderItem extends AbstractEntity<Long> {
    @Column("id_order")
    private Long idOrder;
    @Child(ColumnName = "id_product")
    private Product product;
    private Integer count;

    public OrderItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public OrderItem() {
        super();
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
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
