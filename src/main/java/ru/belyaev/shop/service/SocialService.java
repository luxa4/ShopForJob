// Created by Vologda developer.
// Date: 05.11.2019
// Time: 22:54

package ru.belyaev.shop.service;

import ru.belyaev.shop.model.SocialAccount;

public interface SocialService {

    String getAuthorizeUrl();

    SocialAccount getSocialAccount(String authToken);
}
