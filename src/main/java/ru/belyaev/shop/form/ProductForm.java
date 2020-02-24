// Created by Vologda developer.
// Date: 03.11.2019
// Time: 17:24

package ru.belyaev.shop.form;


public class ProductForm {

    private Integer idProduct;
    private Integer count;


    public ProductForm(Integer idProduct, Integer count) {
        this.idProduct = idProduct;
        this.count = count;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
