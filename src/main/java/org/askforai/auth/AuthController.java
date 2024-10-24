package org.askforai.auth;

import org.askforai.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	// 회원가입 
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody AuthRequest.SignupDTO reqDTO) {
		User user = authService.signup(reqDTO);
		
		return ResponseEntity.ok(user);
	}
	
	
	// 로그인
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody AuthRequest.SigninDTO reqDTO) {
		String token = authService.signin(reqDTO);
		
		return ResponseEntity.ok(token);
	}
	
	
	// 회원탈퇴 @DeleteMapping("/withdrawal")

}
