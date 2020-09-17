package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

@Setter
@Getter
public class UserResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

}
