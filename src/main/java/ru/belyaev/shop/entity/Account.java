// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:06

package ru.belyaev.shop.entity;

import ru.belyaev.shop.model.CurrentAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity<Integer> implements CurrentAccount {
    @Column(name = "name")
    private String name;
    @Column(name = "email")
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

    @Override
    public String getDescription() {
        return name + " (" + email + ") ";
    }

    public Account(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }


    public Account() {
        super();
    }
}
