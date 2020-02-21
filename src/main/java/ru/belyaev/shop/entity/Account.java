// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:06

package ru.belyaev.shop.entity;

import ru.belyaev.shop.model.CurrentAccount;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account implements CurrentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
