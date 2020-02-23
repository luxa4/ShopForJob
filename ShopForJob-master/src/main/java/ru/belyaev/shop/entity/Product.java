// Created by Vologda developer.
// Date: 16.10.2019
// Time: 11:57

package ru.belyaev.shop.entity;


import ru.belyaev.framework.annotationJDBC.Column;

import java.math.BigDecimal;

public class Product extends AbstractEntity<Integer> {
    private String name;
    private String description;
    @Column("image_link")
    private String imageLink;
    private BigDecimal price;
    private String category;
    private String producer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", producer='" + producer + '\'' +
                ", id=" + id +
                '}';
    }
}