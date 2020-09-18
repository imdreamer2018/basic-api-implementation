package com.thoughtworks.rslist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private Integer code;
    private String message;
}
