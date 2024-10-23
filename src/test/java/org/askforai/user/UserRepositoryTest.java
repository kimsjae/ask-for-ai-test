package org.askforai.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	void testFindByUsername() {
		// given
		User user = new User();
		user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        userRepository.save(user);
		
		// when
        Optional<User> foundUser = userRepository.findByUsername("testUser");
		
		// then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testUser");
	}

}
