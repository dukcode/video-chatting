package com.example.video_chatting.service;

import com.example.video_chatting.model.GoogleUser;
import com.example.video_chatting.model.NaverUser;
import com.example.video_chatting.model.OAuth2ProviderUser;
import com.example.video_chatting.model.User;
import com.example.video_chatting.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserService userService;
  private final UserRepository userRepository;
  private final DefaultOAuth2UserService oAuth2UserService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    OAuth2ProviderUser oAuth2ProviderUser = providerUser(clientRegistration, oAuth2User);

    return register(oAuth2ProviderUser);
  }

  public OAuth2User register(OAuth2ProviderUser oAuth2ProviderUser) {
    User user = userRepository.findByUsername(oAuth2ProviderUser.getUsername());

    if (user == null) {
      user = userService.register(oAuth2ProviderUser);
    }

    oAuth2ProviderUser.setId(user.getId());

    return oAuth2ProviderUser;
  }

  public OAuth2ProviderUser providerUser(ClientRegistration clientRegistration,
      OAuth2User oAuth2User) {
    String registrationId = clientRegistration.getRegistrationId();
    if (registrationId.equals("google")) {
      return new GoogleUser(oAuth2User, clientRegistration);
    } else if (registrationId.equals("naver")) {
      return new NaverUser(oAuth2User, clientRegistration);
    }
    return null;
  }

}
