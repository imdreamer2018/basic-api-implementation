package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class User {

    @Size(max = 8)
    @NotNull
    private String userName;

    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;

    @NotNull
    private String gender;

    @Email
    private String email;

    private String phone;

}
