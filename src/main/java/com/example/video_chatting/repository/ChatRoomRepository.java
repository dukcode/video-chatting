package com.example.video_chatting.repository;

import com.example.video_chatting.model.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

  List<ChatRoom> findAllByOrderByCreatedAtDesc();
}
