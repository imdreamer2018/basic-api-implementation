package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String userName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

}
