package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.dto.UserRequest;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RsEventRequest implements Serializable{

    private Integer eventId;

    private String eventName;

    private String keyWord;

    @NotNull
    private Integer userId;

    @Builder.Default
    private Integer voteNum = 10;

    public RsEventRequest(String eventName, String keyWord, @NotNull Integer userId) {
        this.eventName = eventName;
        this.keyWord = keyWord;
        this.userId = userId;
    }
}
