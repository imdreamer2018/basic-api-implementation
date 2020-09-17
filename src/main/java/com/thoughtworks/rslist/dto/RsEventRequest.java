package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RsEventRequest implements Serializable {

    private String eventName;

    private String keyWord;

    @Valid
    private UserRequest userRequest;

    @JsonIgnore
    public UserRequest getUserRequest() {
        return userRequest;
    }

    @JsonProperty
    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
