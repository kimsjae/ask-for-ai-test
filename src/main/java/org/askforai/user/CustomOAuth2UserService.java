package org.askforai.user;

import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;


 @Override
 public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
     OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = 
         new DefaultOAuth2UserService();

     OAuth2User oAuth2User = delegate.loadUser(userRequest);

     String username = oAuth2User.getAttribute("email"); // 이메일을 사용자 이름으로 사용
     User user = userRepository.findByUsername(username).orElseGet(() -> {
         User newUser = User.builder()
                 .username(username)
                 .email(username) // 이메일 저장
                 .password(passwordEncoder.encode(UUID.randomUUID().toString())) // 랜덤 비밀번호 저장
                 .build();
         return userRepository.save(newUser);
     });

     return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), oAuth2User.getAttributes(), "email");
 }
}
