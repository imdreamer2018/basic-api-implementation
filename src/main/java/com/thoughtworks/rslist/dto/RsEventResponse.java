package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RsEventResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
}
