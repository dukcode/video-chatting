package com.example.video_chatting.controller;

import com.example.video_chatting.dto.CreateChatRoomRequest;
import com.example.video_chatting.model.OAuth2ProviderUser;
import com.example.video_chatting.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@RequiredArgsConstructor
@RequestMapping("/chat-rooms")
@Controller
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @GetMapping
  public String chatRoomList(Model model, @AuthenticationPrincipal OAuth2ProviderUser oauth2User) {
    model.addAttribute("rooms", chatRoomService.findAllRooms());
    model.addAttribute("user", oauth2User.getAttributes());
    return "chat/chat-rooms";
  }

  @PostMapping
  public String chatRoomList(@ModelAttribute CreateChatRoomRequest request,
      @AuthenticationPrincipal OAuth2ProviderUser oauth2User) {
    chatRoomService.createChatRoom(request);
    return "redirect:/chat-rooms";
  }
}
