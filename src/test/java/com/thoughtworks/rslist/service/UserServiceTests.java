package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
import com.thoughtworks.rslist.exception.BaseUserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    void should_return_user_info_when_create_user_success() {
        User user = new User("yangqian1",18,"male","qian.yang@twu.com","17607114747");
        UserResponse<User> response = userService.registerUser(user);
        assertEquals("register user success!", response.getMessage());
    }

    @Test
    void should_return_exception_when_register_user_existed() {
        User user = new User("yangqian",18,"male","qian.yang@twu.com","17607114747");
        BaseUserException baseUserException = assertThrows(BaseUserException.class, () -> userService.registerUser(user));
        assertEquals("user name is existed!", baseUserException.getMessage());
    }
}
