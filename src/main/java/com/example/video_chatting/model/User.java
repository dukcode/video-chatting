package com.example.video_chatting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"user\"")
@Entity
public class User {

  @GeneratedValue
  @Id
  private Long id;

  @Column(nullable = false, unique = true, updatable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  private String provider;

  @Builder
  public User(String username, String password, String nickname, String name, String email,
      String provider) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.name = name;
    this.email = email;
    this.provider = provider;
  }

  public void setNickname(String uniqueNickname) {
    nickname = uniqueNickname;
  }
}
