package org.askforai.user;

import org.askforai._core.utils.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/api/registration")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest.RegisterDTO reqDTO) {
		User sessionUser = userService.registerUser(reqDTO);

		return ResponseEntity.ok(new ApiUtil<>(sessionUser));
	}

}
