package com.example.video_chatting.service;

import com.example.video_chatting.model.OAuth2ProviderUser;
import com.example.video_chatting.model.User;
import com.example.video_chatting.repository.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final Random random = new Random();

  public User register(OAuth2ProviderUser oAuth2ProviderUser) {
    String originalNickname = oAuth2ProviderUser.getNickname();
    String uniqueNickname = originalNickname;

    while (isNicknameTaken(uniqueNickname)) {
      uniqueNickname = originalNickname + generateRandomNumber();
    }

    User user = oAuth2ProviderUser.toUser();
    user.setNickname(uniqueNickname);

    return userRepository.save(user);
  }

  private boolean isNicknameTaken(String nickname) {
    return userRepository.existsByNickname(nickname);
  }

  private String generateRandomNumber() {
    int number = random.nextInt(1000000); // 0부터 999999까지의 숫자 생성
    return String.format("%06d", number); // 6자리로 포맷팅
  }
}
