package org.askforai.user;

import org.askforai._core.utils.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	// 회원가입
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest.RegisterDTO reqDTO) {
		User sessionUser = userService.registerUser(reqDTO);

		return ResponseEntity.ok(new ApiUtil<>(sessionUser));
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO reqDTO) {
		User user = userService.login(reqDTO);
		
		return ResponseEntity.ok(new ApiUtil<>(user));
	}

}
