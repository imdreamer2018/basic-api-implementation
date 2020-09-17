package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static List<UserRequest> userRequestList = initUser();

    private static List<UserRequest> initUser() {
        List<UserRequest> userRequestList = new ArrayList<>();
        userRequestList.add(new UserRequest("yangqian",24,"male","qian.yang@twu.com","17607114747"));
        userRequestList.add(new UserRequest("nannan",23,"female","yn.guo@twu.com","17607114747"));
        return userRequestList;
    }

    public UserResponse<UserRequest> registerUser(UserRequest userRequest) {
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        if (verifyUserIsExited(userRequest.getUserName())) {
            userResponse.setCode(200);
            userResponse.setMessage("register user that username is existed");
        } else {
            userRequestList.add(userRequest);
            userResponse.setCode(201);
            userResponse.setMessage("register user success!");
            userResponse.setData(userRequest);
        }
        return userResponse;
    }

    public boolean verifyUserIsExited(String userName) {
        return userRequestList.stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    public UserResponse<UserRequest> getUser(String username) {
        UserResponse<UserRequest> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        List<UserRequest> userRequestInfoList = userRequestList.stream()
                .filter(user -> user.getUserName().equals(username))
                .collect(Collectors.toList());
        if (userRequestInfoList.isEmpty()) {
            userResponse.setMessage("can not find this user!");
            return userResponse;
        }
        userResponse.setMessage("get user info success!");
        userResponse.setData(userRequestInfoList.get(0));
        return userResponse;
    }

    public ResponseEntity<UserResponse<List<UserRequest>>> getAllUser() {
        UserResponse<List<UserRequest>> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get all user info success!");
        userResponse.setData(userRequestList);

        return ResponseEntity.ok().body(userResponse);
    }
}
