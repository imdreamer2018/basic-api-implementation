package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class User {

    @Size(max = 8)
    private String userName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

}
