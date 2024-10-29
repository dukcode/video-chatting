package com.example.video_chatting.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {

  private String sender;
  private String content;
  private Long chatRoomId;
}
