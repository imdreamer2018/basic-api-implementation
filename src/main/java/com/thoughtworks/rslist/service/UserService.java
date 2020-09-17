package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static List<User> userList = initUser();

    private static List<User> initUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("yangqian",24,"male","qian.yang@twu.com","17607114747"));
        userList.add(new User("nannan",23,"female","yn.guo@twu.com","17607114747"));
        return userList;
    }

    public UserResponse<User> registerUser(User user) {
        UserResponse<User> userResponse = new UserResponse<>();
        if (verifyUserIsExited(user.getUserName())) {
            userResponse.setCode(200);
            userResponse.setMessage("register user that username is existed");
        } else {
            userList.add(user);
            userResponse.setCode(201);
            userResponse.setMessage("register user success!");
            userResponse.setData(user);
        }
        return userResponse;
    }

    public boolean verifyUserIsExited(String userName) {
        return userList.stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    public UserResponse<User> getUser(String username) {
        UserResponse<User> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        List<User> userInfoList = userList.stream()
                .filter(user -> user.getUserName().equals(username))
                .collect(Collectors.toList());
        if (userInfoList.isEmpty()) {
            userResponse.setMessage("can not find this user!");
            return userResponse;
        }
        userResponse.setMessage("get user info success!");
        userResponse.setData(userInfoList.get(0));
        return userResponse;
    }

    public ResponseEntity<UserResponse<List<User>>> getAllUser() {
        UserResponse<List<User>> userResponse = new UserResponse<>();
        userResponse.setCode(200);
        userResponse.setMessage("get all user info success!");
        userResponse.setData(userList);

        return ResponseEntity.ok().body(userResponse);
    }
}
