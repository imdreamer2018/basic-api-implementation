package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class User {

    @Size(max = 8)
    @NotNull
    private String userName;

    @NotNull
    private Integer age;

    private String gender;

    private String email;

    private String phone;

}
