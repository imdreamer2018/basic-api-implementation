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


    public static List<UserRequest> userRequestList = new ArrayList<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public ResponseEntity<UserResponse<UserRequest>> registerUser(UserRequest userRequest) {
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        Optional<UserEntity> user = userRepository.findByUserName(userRequest.getUserName());
        if (user.isPresent()) {
            throw new BaseUserException("register user that username is existed");
        } else {
            UserEntity userEntity = UserEntity.builder()
                    .userName(userRequest.getUserName())
                    .age(userRequest.getAge())
                    .email(userRequest.getEmail())
                    .gender(userRequest.getGender())
                    .phone(userRequest.getPhone())
                    .voteNum(userRequest.getVoteNum())
                    .build();
            userRepository.save(userEntity);
            userRequest.setUserId(userEntity.getId());
            userResponse.setCode(201);
            userResponse.setMessage("register user success!");
            userResponse.setData(userRequest);
        }
        return ResponseEntity.created(URI.create("/users")).body(userResponse);
    }


    public ResponseEntity<UserResponse<UserRequest>> getUser(Integer userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new BaseUserException("can not find this user!");
        }
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get user info success!");
        userResponse.setData(from(user.get()));
        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse<List<UserRequest>>> getAllUser() {
        List<UserEntity> allUsers = userRepository.findAll();
        UserResponse<List<UserRequest>> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get all user info success!");
        userResponse.setData(allUsers.stream()
                .map(UserService::from)
                .collect(Collectors.toList()));

        return ResponseEntity.ok().body(userResponse);
    }

    public ResponseEntity<UserResponse<UserRequest>> deleteUserById(Integer userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        if (!user.isPresent()) {
            throw new BaseUserException("the user is not existed!");
        }
        userRepository.deleteById(userId);
        userResponse.setCode(200);
        userResponse.setMessage("delete user success!");
        userResponse.setData(from(user.get()));
        return ResponseEntity.ok(userResponse);
    }

    private static UserRequest from(UserEntity userEntity) {
        return UserRequest.builder()
                .userId(userEntity.getId())
                .userName(userEntity.getUserName())
                .age(userEntity.getAge())
                .gender(userEntity.getGender())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .build();
    }
}
