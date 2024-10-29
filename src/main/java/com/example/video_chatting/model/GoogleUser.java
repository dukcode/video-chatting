package com.example.video_chatting.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends OAuth2ProviderUser {

  public GoogleUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
    super(oAuth2User.getAttributes(), clientRegistration);
  }


  @Override
  public String getUsername() {
    return getProvider() + ":" + getAttributes().get("sub");
  }

  @Override
  public String getNickname() {
    return (String) getAttributes().get("name");
  }

}
