package com.thoughtworks.rslist.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VoteResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

}
