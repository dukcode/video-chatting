package com.example.video_chatting.service;

import com.example.video_chatting.dto.CreateChatRoomRequest;
import com.example.video_chatting.model.ChatRoom;
import com.example.video_chatting.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;
  private final PasswordEncoder passwordEncoder;

  public List<ChatRoom> findAllRooms() {
    return chatRoomRepository.findAllByOrderByCreatedAtDesc();
  }

  public void createChatRoom(CreateChatRoomRequest request) {
    ChatRoom chatRoom = request.toEntity();
    chatRoom.encodePassword(passwordEncoder);
    chatRoomRepository.save(chatRoom);
  }

}
