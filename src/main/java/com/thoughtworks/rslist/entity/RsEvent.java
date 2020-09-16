package com.thoughtworks.rslist.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsEvent {

    private String eventName;

    private String keyWord;

    public RsEvent(String eventName, String keyWord) {
        this.eventName = eventName;
        this.keyWord = keyWord;
    }
}
