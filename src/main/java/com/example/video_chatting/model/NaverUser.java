package com.example.video_chatting.model;

import java.util.Map;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class NaverUser extends OAuth2ProviderUser {

  public NaverUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
    super((Map<String, Object>) oAuth2User.getAttributes().get("response"), clientRegistration);
  }


  @Override
  public String getUsername() {
    return getProvider() + ":" + getAttributes().get("id");
  }

  @Override
  public String getNickname() {
    return (String) getAttributes().get("nickname");
  }
}
