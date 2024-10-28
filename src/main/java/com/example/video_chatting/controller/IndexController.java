package com.example.video_chatting.controller;

import com.example.video_chatting.model.OAuth2ProviderUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index(Model model, @AuthenticationPrincipal OAuth2ProviderUser oAuth2User) {
    if (oAuth2User != null) {
      model.addAttribute("user", oAuth2User);
    }
    return "index";
  }

}
