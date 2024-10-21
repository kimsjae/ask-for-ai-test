package org.askforai.user;

import lombok.Data;

public class UserRequest {
	
	// 회원가입 DTO
	@Data
	public static class RegisterDTO {
		private String username;
		private String password;
		private String email;
		
		public User toEntity() {
			return User.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
		}
	}

}
