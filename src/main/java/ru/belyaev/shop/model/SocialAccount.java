// Created by Vologda developer.
// Date: 05.11.2019
// Time: 22:52

package ru.belyaev.shop.model;

import org.springframework.stereotype.Component;

@Component
public class SocialAccount {
    private String name;
    private String email;

    public SocialAccount() {
    }

    public SocialAccount(String name, String email) {
        this.name = name;
        this.email = email;
    }

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
}
