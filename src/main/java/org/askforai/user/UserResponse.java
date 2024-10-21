package org.askforai.user;

import lombok.Data;

public class UserResponse {
	
	@Data
	public static class RegisterDTO {
		private String username;
		private String password;
		private String email;
	}

}
