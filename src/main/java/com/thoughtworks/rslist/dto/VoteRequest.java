package com.thoughtworks.rslist.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest implements Serializable {


    private Integer id;

    @NotNull
    private Integer voteNum;

    private LocalDateTime voteTime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer rsEventId;

}
