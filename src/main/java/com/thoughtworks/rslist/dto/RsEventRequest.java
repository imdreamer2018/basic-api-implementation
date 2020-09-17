package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RsEventRequest {

    private String eventName;

    private String keyWord;

    private Integer userId;
}
