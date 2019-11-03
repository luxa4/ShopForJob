// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:06

package ru.belyaev.shop.entity;

public class Account extends AbstractEntity<Integer> {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
