package com.example.video_chatting.config;

import com.example.video_chatting.repository.UserRepository;
import com.example.video_chatting.service.CustomOAuth2UserService;
import com.example.video_chatting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

  private final UserRepository userRepository;
  private final UserService userService;

  @Bean
  public CustomOAuth2UserService customOAuth2UserService() {
    return new CustomOAuth2UserService(userService, userRepository, new DefaultOAuth2UserService());
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/static/**");
  }

  @Bean
  SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(((requests) -> requests
        .requestMatchers("/", "/logout").permitAll()
        .anyRequest().authenticated()));

    http.oauth2Login(oauth2 -> oauth2
        .userInfoEndpoint(
            userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService()))
        .loginPage("/"));

    http.logout(logout -> logout.logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
    );

    return http.build();
  }

  /*
  @Bean // hasAuthority 일경우 정의하지 않는다
  public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
    SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
    simpleAuthorityMapper.setPrefix("ROLE_");
    return simpleAuthorityMapper;
  }
  */

}
