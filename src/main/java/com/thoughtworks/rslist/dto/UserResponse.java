package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse<T> {
    private Integer code;
    private String message;
    private T data;
}
