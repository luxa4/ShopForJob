// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:07

package ru.belyaev.shop.entity;


import ru.belyaev.framework.annotationJDBC.Child;
import ru.belyaev.framework.annotationJDBC.Column;

public class OrderItem extends AbstractEntity<Long> {
    @Column("id_order")
    private Integer idOrder;
    @Child(ColumnName = "id_product")
    private Product product;
    @Column("id_account")
    private Integer idAccount;
    private Integer count;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getIdAccoutn() {
        return idAccount;
    }

    public void setIdAccoutn(Integer idAccoutn) {
        this.idAccount = idAccount;
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
                ", idAccount=" + idAccount +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
