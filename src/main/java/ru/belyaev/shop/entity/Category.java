// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:02

package ru.belyaev.shop.entity;


import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "product_count")
    private int productCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", productCount=" + productCount +
                ", id=" + id +
                '}';
    }
}
