package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserResponse;
import com.thoughtworks.rslist.entity.User;
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
        userList.add(user);
        UserResponse<User> userResponse = new UserResponse<>();
        userResponse.setCode(201);
        userResponse.setMessage("register user success!");
        userResponse.setData(user);
        return userResponse;
    }

}
