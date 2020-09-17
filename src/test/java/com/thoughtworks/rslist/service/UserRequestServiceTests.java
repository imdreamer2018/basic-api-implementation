package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.exception.BaseUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRequestServiceTests {

    @Autowired
    UserService userService;

    @Test
    void should_return_user_info_when_create_user_success() {
        UserRequest userRequest = new UserRequest("yangqian1",18,"male","qian.yang@twu.com","17607114747");
        UserResponse<UserRequest> response = userService.registerUser(userRequest);
        assertEquals("register user success!", response.getMessage());
    }

    @Test
    void should_return_exception_when_register_user_existed() {
        UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");
        BaseUserException baseUserException = assertThrows(BaseUserException.class, () -> userService.registerUser(userRequest));
        assertEquals("user name is existed!", baseUserException.getMessage());
    }

    @Test
    void should_return_user_info_when_get_user_by_username_success() {
        UserResponse<UserRequest> userUserResponse = userService.getUser("yangqian");
        assertEquals("get user info success!", userUserResponse.getMessage());
    }

    @Test
    void should_return_null_user_info_when_get_user_by_username() {
        UserResponse<UserRequest> userUserResponse = userService.getUser("312312");
        assertEquals("can not find this user!", userUserResponse.getMessage());
    }

    @Test
    void should_return_all_users_info_when_get_users() {
        ResponseEntity<UserResponse<List<UserRequest>>> response = userService.getAllUser();
        assertEquals("get all user info success!", Objects.requireNonNull(response.getBody()).getMessage());
    }
}
