package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable{

    private Integer userId;

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

    @Pattern(regexp = "1(\\d){10}")
    private String phone;

    private Integer voteNum = 10;

    public UserRequest(@Size(max = 8) @NotNull String userName, @NotNull @Min(18) @Max(100) Integer age, @NotNull String gender, @Email String email, @Pattern(regexp = "1(\\d){10}") String phone) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
