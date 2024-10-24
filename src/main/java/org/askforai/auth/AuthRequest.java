package org.askforai.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class AuthRequest {
	
	@Data
	public static class SignupDTO {

	    @NotBlank(message = "username은 최소 4자리, 최대 12자리입니다.")
	    @Size(min = 4, max = 12)
	    private String username;

	    @NotBlank(message = "password는 최소 4자리, 최대 12자리입니다.")
	    @Size(min = 4, max = 12)
	    private String password;

	    @NotBlank(message = "name은 최소 4자리, 최대 12자리입니다.")
	    @Size(min = 4, max = 12)
	    private String name;

	    @Email
	    @NotBlank(message = "username은 최소 4자리, 최대 12자리입니다.")
	    @Size(min = 4, max = 12)
	    private String email;
	}

}
