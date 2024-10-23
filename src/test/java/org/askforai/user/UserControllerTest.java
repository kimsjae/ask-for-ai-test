package org.askforai.user;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.askforai.user.UserRequest.LoginDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

	@Test
	void testLoginSuccess() throws Exception {
		// given
		User user = new User();
		user.setUsername("asdasd");
		user.setPassword("asdasdasd");
		Mockito.when(userRepository.findByUsername("asdasd")).thenReturn(Optional.of(user));
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsername("asdasd");
		loginDTO.setPassword("asdasdasd");
		
		// when
		Mockito.when(userService.login(loginDTO)).thenReturn(user);
		
		// then
		mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)))
				.andExpect(jsonPath("$.status", is(200))) 
		        .andExpect(jsonPath("$.msg", is("성공")))
		        .andExpect(jsonPath("$.body.username", is("asdasd"))); 
	}

}
