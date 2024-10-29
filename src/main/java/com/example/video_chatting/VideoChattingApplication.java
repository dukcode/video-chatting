package com.example.video_chatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VideoChattingApplication {

  public static void main(String[] args) {
    SpringApplication.run(VideoChattingApplication.class, args);
  }

}
