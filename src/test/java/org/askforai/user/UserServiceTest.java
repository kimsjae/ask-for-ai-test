package org.askforai.user;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.askforai.user.UserRequest.LoginDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    
    private User user;
    
    @BeforeEach
    void setUp() {
    	user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
    }
    
	@Test
	void testLoginSuccess() {
		// given
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // when
        User result = userService.login(loginDTO);

        // then
        Assertions.assertEquals(user, result);
	}

}
