package com.example.video_chatting.controller;

import com.example.video_chatting.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/send/{chatRoomId}")
  @SendTo("/topic/messages/{chatRoomId}")
  public ChatMessage sendMessage(@DestinationVariable("chatRoomId") Long chatRoomId,
      ChatMessage message) {
    return message;
  }
}
