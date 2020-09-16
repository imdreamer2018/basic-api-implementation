package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    User user = new User("yangqian",18,"male","qian.yang@twu.com","17607114747");

    @Test
    void should_return_user_info_when_create_user_success() {
        UserResponse<User> response = userService.registerUser(user);
        assertEquals("register user success!", response.getMessage());
    }
}
