package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Setter
@Getter
public class UserResponse<T> {
    private Integer code;
    private String message;
    private T data;

}
