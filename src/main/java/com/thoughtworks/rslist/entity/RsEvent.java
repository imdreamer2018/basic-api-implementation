package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RsEvent {

    private String eventName;

    private String keyWord;

    private User user;


}
