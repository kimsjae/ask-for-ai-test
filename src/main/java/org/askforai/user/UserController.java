package org.askforai.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    // 내정보 보기

//    @PostMapping("/signup")
//    public ResponseEntity<User> signup(@RequestBody UserRequest.SignupDTO signupDTO) {
//        User newUser = userService.signup(signupDTO);
//        return ResponseEntity.status(201).body(newUser);
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<String> signin(@RequestBody UserRequest.SigninDTO signinDTO) {
//        String jwtToken = userService.signin(signinDTO);
//        return ResponseEntity.ok(jwtToken);
//    }
//    
//    @GetMapping("/oauth2/callback")
//    public ResponseEntity<String> oauth2Login(@RequestParam String code) {
//        // OAuth2 로그인 처리를 위한 로직 추가
//        // 성공적으로 로그인하면 JWT 반환
//        return ResponseEntity.ok("로그인 성공, JWT 토큰 반환"); // JWT 반환하는 로직 추가 필요
//    }
//    
//    @GetMapping("/login/oauth2/callback/google")
//    public ResponseEntity<String> googleLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        // 사용자 정보를 사용하여 JWT 토큰 생성
//        String username = oAuth2User.getAttribute("email");
//        String token = userService.signin(new UserRequest.OAuth2SigninDTO(username));
//        
//        return ResponseEntity.ok(token);
//    }


}
