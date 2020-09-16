package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {

    private String userName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

}
