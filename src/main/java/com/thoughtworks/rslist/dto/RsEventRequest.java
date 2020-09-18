package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RsEventRequest {

    private Integer eventId;

    private String eventName;

    private String keyWord;

    @NotNull
    private Integer userId;

    private Integer voteNum;

    public RsEventRequest(String eventName, String keyWord, @NotNull Integer userId) {
        this.eventName = eventName;
        this.keyWord = keyWord;
        this.userId = userId;
        this.voteNum = 0;
    }
}
