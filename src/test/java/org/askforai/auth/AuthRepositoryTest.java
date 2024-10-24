package org.askforai.auth;

import org.askforai.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AuthRepositoryTest {
	
	@Autowired
	private AuthRepository authRepository;

	@Test
	void testExistsByUsername() {
		
		String username = "asdasd";
		
		User user = User.builder()
				.username(username)
				.build();
		
		authRepository.findByUsername(username);
		
	}

	@Test
	void testExistsByEmail() {
	}

}
