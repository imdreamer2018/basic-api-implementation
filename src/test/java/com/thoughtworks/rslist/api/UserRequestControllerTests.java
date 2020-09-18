package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRequestControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void should_return_status_create_when_create_user_success() throws Exception {
        UserRequest userRequest = new UserRequest("hello",18,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_status_bad_request_when_register_user_is_existed() throws Exception {
        UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_username_length_more_than_8() throws Exception {
        UserRequest userRequest = new UserRequest("yangqian123",18,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_username_is_null() throws Exception {
        UserRequest userRequest = new UserRequest("",18,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_age_is_null() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",null,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_age_less_than_18() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",14,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_age_more_than_100() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",111,"male","qian.yang@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_gender_is_null() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",15,null,"1@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_email_is_invalid_input() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",15,"male","@twu.com","17607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_phone_number_start_not_is_1() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",15,"male","1@twu.com","27607114747");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_bad_request_when_register_user_of_phone_number_length_is_not_11() throws Exception {
        UserRequest userRequest = new UserRequest("hhh",15,"male","1@twu.com","1760711474");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_ok_when_get_user_by_id_success() throws Exception {
        userService.registerUser(UserRequest.builder()
                .userName("yangqian")
                .age(18)
                .email("743295483@qq.com")
                .gender("male")
                .phone("17607114747")
                .build());
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_bad_request_when_get_user_by_id() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_status_ok_when_get_user_success() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_ok_when_delete_user_success() throws Exception {
        userService.registerUser(UserRequest.builder()
                .userName("yangqian")
                .age(18)
                .email("743295483@qq.com")
                .gender("male")
                .phone("17607114747")
                .build());
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_bad_request_when_delete_user_is_not_existed() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isBadRequest());
    }
}
