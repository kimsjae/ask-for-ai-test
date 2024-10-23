package org.askforai.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserRequest {
	
	// 회원가입 DTO
	@Data
	public static class SignupDTO {
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
	
	// 로그인 DTO
	@Data
	public static class SigninDTO {

		@NotBlank(message = "사용자 이름은 필수입니다.")
		@Size(min = 4, max = 12, message = "사용자 이름은 4자 이상, 20자 이하이어야 합니다.")
		private String username;
		
		@NotBlank(message = "비밀번호는 필수입니다.")
		@Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하이어야 합니다.")
		private String password;
	}
	
	// OAuth 로그인
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OAuth2SigninDTO {
	    private String username; // 이메일만 필요
	}

}
