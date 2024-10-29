package com.example.video_chatting.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public abstract class OAuth2ProviderUser implements OAuth2User {

  private final Map<String, Object> attributes;
  private final ClientRegistration clientRegistration;

  private Long id;

  public OAuth2ProviderUser(Map<String, Object> attributes, ClientRegistration clientRegistration) {
    this.attributes = attributes;
    this.clientRegistration = clientRegistration;
  }

  public String getPassword() {
    return UUID.randomUUID().toString();
  }

  public String getEmail() {
    return (String) attributes.get("email");
  }

  public String getName() {
    return (String) attributes.get("name");
  }

  public String getProvider() {
    return clientRegistration.getRegistrationId();
  }

  abstract public String getUsername();

  abstract public String getNickname();

  public User toUser() {
    return User.builder()
        .username(getUsername())
        .password(getPassword())
        .nickname(getNickname())
        .name(getName())
        .email(getEmail())
        .provider(getProvider())
        .build();
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }
}
