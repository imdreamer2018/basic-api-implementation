package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.exception.BaseUserException;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;


    public static List<UserRequest> userRequestList = initUser();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private static List<UserRequest> initUser() {
        List<UserRequest> userRequestList = new ArrayList<>();
        userRequestList.add(new UserRequest("yangqian",24,"male","qian.yang@twu.com","17607114747"));
        userRequestList.add(new UserRequest("nannan",23,"female","yn.guo@twu.com","17607114747"));
        return userRequestList;
    }

    public ResponseEntity<UserResponse<UserRequest>> registerUser(UserRequest userRequest) {
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        Optional<UserEntity> user = userRepository.findByUserName(userRequest.getUserName());
        if (user.isPresent()) {
            throw new BaseUserException("register user that username is existed");
        } else {
            userRepository.save(UserEntity.builder()
                    .userName(userRequest.getUserName())
                    .age(userRequest.getAge())
                    .email(userRequest.getEmail())
                    .gender(userRequest.getGender())
                    .phone(userRequest.getPhone())
                    .build());

            userResponse.setCode(201);
            userResponse.setMessage("register user success!");
            userResponse.setData(userRequest);
        }
        return ResponseEntity.created(URI.create("/users")).body(userResponse);
    }


    public ResponseEntity<UserResponse<UserEntity>> getUser(Integer userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new BaseUserException("can not find this user!");
        }
        UserResponse<UserEntity> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get user info success!");
        userResponse.setData(user.get());
        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse<List<UserEntity>>> getAllUser() {
        List<UserEntity> allUsers = userRepository.findAll();
        UserResponse<List<UserEntity>> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get all user info success!");
        userResponse.setData(allUsers);

        return ResponseEntity.ok().body(userResponse);
    }

    public ResponseEntity<UserResponse<UserEntity>> deleteUserById(Integer userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        UserResponse<UserEntity> userResponse = new UserResponse<>();
        if (!user.isPresent()) {
            throw new BaseUserException("the user is not existed!");
        }
        userRepository.deleteById(userId);
        userResponse.setCode(200);
        userResponse.setMessage("delete user success!");
        userResponse.setData(user.get());
        return ResponseEntity.ok(userResponse);
    }
}
