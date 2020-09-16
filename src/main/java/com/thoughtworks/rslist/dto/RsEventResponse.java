package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsEventResponse<T> {

    private Integer code;
    private String message;
    private T data;
}
