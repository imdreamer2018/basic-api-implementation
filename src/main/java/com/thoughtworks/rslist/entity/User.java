package com.thoughtworks.rslist.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class User {

    @Size(max = 8)
    @NotEmpty
    @JsonProperty("user_name")
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

}
