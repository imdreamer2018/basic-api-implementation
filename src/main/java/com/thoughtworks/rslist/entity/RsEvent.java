package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Getter
@Setter
@AllArgsConstructor
public class RsEvent {

    private String eventName;

    private String keyWord;

    @Valid
    private User user;


}
