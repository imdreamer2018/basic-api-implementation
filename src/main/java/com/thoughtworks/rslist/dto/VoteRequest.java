package com.thoughtworks.rslist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Time;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
public class VoteRequest {


    private Integer id;

    @NotNull
    private Integer voteNum;

    private String voteTime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer rsEventId;

    public VoteRequest(@NotNull Integer voteNum, @NotNull Integer userId, @NotNull Integer rsEventId) {
        this.voteNum = voteNum;
        this.voteTime = this.getNowTime();
        this.userId = userId;
        this.rsEventId = rsEventId;
    }

    private String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return sdf.format(new java.util.Date());
    }
}
