package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
import com.thoughtworks.rslist.exception.BaseUserException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    static List<User> userList = initUser();

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

    private boolean verifyUserIsExited(String userName) {
        if (userList.stream().anyMatch(user -> user.getUserName().equals(userName))) {
            return true;
        }
        return false;
    }

}
