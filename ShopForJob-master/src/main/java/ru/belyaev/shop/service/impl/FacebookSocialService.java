// Created by Vologda developer.
// Date: 05.11.2019
// Time: 22:55

package ru.belyaev.shop.service.impl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.service.SocialService;

public class FacebookSocialService implements SocialService {
    private final String idClient;
    private final String secret;
    private final String redirectUrl;


    public FacebookSocialService(ServiceManager serviceManager) {
        idClient = serviceManager.getApplicationProperty("social.facebook.idClient");
        secret = serviceManager.getApplicationProperty("social.facebook.secret");
        redirectUrl = serviceManager.getApplicationProperty("app.host") + "/from-social";
    }

    @Override
    public String getAuthorizeUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL); // разрешение на получение email
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_10);
        return client.getLoginDialogUrl(idClient, redirectUrl, scopeBuilder);
    }


    @Override
    public SocialAccount getSocialAccount(String authToken) {
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_10);
        FacebookClient.AccessToken accessToken = client.obtainUserAccessToken(idClient, secret, redirectUrl, authToken);
        client = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_2_10);
        User user = client.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name"));
        System.out.println(user.getFirstName());
        System.out.println(user.getEmail());
        return new SocialAccount(user.getFirstName(), user.getEmail());
    }
}
