package com.example.video_chatting.dto;

import com.example.video_chatting.model.ChatRoom;
import lombok.Data;

@Data
public class CreateChatRoomRequest {

  private String name;
  private String password;

  public ChatRoom toEntity() {
    return ChatRoom.builder()
        .name(name)
        .password(password)
        .build();
  }
}
