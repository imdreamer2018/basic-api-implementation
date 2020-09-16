package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
import com.thoughtworks.rslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse<User> registerUser(@Validated @RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("users/{username}")
    @ResponseBody
    public UserResponse<User> getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

}
