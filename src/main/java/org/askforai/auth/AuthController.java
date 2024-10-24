package org.askforai.auth;

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
		
		String token = authService.signup(reqDTO);
		
		return ResponseEntity.ok(token);
	}
	
	
	// 로그인 @PostMapping("/signin")
	
	
	
	// 회원탈퇴 @DeleteMapping("/withdrawal")

}
