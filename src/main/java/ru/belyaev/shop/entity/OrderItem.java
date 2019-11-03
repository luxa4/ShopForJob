// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:07

package ru.belyaev.shop.entity;

public class OrderItem extends AbstractEntity<Long> {
    private Integer idOrder;
    private Integer idProduct;
    private Integer idAccoutn;
    private Integer count;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdAccoutn() {
        return idAccoutn;
    }

    public void setIdAccoutn(Integer idAccoutn) {
        this.idAccoutn = idAccoutn;
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
                ", idProduct=" + idProduct +
                ", idAccoutn=" + idAccoutn +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
