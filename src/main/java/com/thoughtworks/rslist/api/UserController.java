package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse<UserRequest>> registerUser(@Validated @RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @GetMapping("users/{userId}")
    @ResponseBody
    public ResponseEntity<UserResponse<UserEntity>> getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping("users")
    @ResponseBody
    public ResponseEntity getAllUsers() {
        return userService.getAllUser();
    }

}
