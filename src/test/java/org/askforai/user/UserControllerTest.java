package org.askforai.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // 추가
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc // 추가
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void registerUser_success_test() throws Exception {
        UserRequest.RegisterDTO reqDTO = new UserRequest.RegisterDTO();
        
        reqDTO.setUsername("asd");
        reqDTO.setPassword("asd");
        reqDTO.setEmail("asd@asd");
        
        String reqBody = objectMapper.writeValueAsString(reqDTO);
        
        mockMvc.perform(
                post("/registration")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.msg").value("성공"))
                .andExpect(jsonPath("$.body.username").value("asd"))
                .andExpect(jsonPath("$.body.password").value("asd"))
                .andExpect(jsonPath("$.body.email").value("asd@asd"));
    }
}
