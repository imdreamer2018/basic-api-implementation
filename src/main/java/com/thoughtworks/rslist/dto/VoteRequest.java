package com.thoughtworks.rslist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
public class VoteRequest {


    private Integer id;

    @NotNull
    private Integer voteNum;

    private Date voteTime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer rsEventId;

    public VoteRequest(@NotNull Integer voteNum, @NotNull Integer userId, @NotNull Integer rsEventId) {
        this.voteNum = voteNum;
        this.voteTime = new Date(Calendar.getInstance().getTime().getTime());
        this.userId = userId;
        this.rsEventId = rsEventId;
    }
}
