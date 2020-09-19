package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.entity.VoteEntity;
import com.thoughtworks.rslist.exception.BaseRsListException;
import com.thoughtworks.rslist.exception.BaseUserException;
import com.thoughtworks.rslist.exception.BaseVoteException;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.repository.VoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoteServiceTests {


    @Autowired
    VoteService voteService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RsEventRepository rsEventRepository;

    @Autowired
    VoteRepository voteRepository;

    @BeforeEach
    void setUp() {
        UserEntity user = UserEntity.builder()
                .userName("123")
                .age(18)
                .gender("male")
                .email("123@123.com")
                .phone("17607114747")
                .voteNum(10)
                .build();
        userRepository.save(user);

        RsEventEntity rsEventEntity = RsEventEntity.builder()
                .keyWord("经济")
                .eventName("猪肉")
                .voteNum(10)
                .user(user)
                .build();
        rsEventRepository.save(rsEventEntity);

        VoteEntity voteEntity = VoteEntity.builder()
                .voteNum(1)
                .voteTime(new Timestamp((System.currentTimeMillis())))
                .user(user)
                .rsEvent(rsEventEntity)
                .build();
        voteRepository.save(voteEntity);
    }

    @AfterEach
    void endUp() {
//        voteRepository.deleteAll();
//        rsEventRepository.deleteAll();
//        userRepository.deleteAll();
    }

    private static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return sdf.format(new java.util.Date());
    }

    @Test
    void should_return_all_votes_when_get_all_votes() {

        ResponseEntity<VoteResponse<List<VoteRequest>>> votes = voteService.getVotes();
        assertEquals(200, votes.getStatusCodeValue());
        assertEquals("get votes success!", Objects.requireNonNull(votes.getBody()).getMessage());
    }

    @Test
    void should_return_vote_when_get_votes_by_vote_id() {
        ResponseEntity<VoteResponse<VoteRequest>> vote = voteService.getVoteByVoteId(1);
        assertEquals(200, vote.getStatusCodeValue());
        assertEquals("get vote success!", Objects.requireNonNull(vote.getBody()).getMessage());
    }

    @Test
    void should_throw_bad_request_when_get_votes_by_vote_id_can_not_found() {
        voteRepository.deleteAll();
        BaseVoteException baseVoteException = assertThrows(BaseVoteException.class, () -> voteService.getVoteByVoteId(1));
        assertEquals("can not find this vote!", baseVoteException.getMessage());
    }

    @Test
    void should_return_vote_info_when_create_vote_by_user_id_and_event_id() {
        VoteRequest voteRequest = VoteRequest.builder()
                .voteNum(1)
                .voteTime(new Timestamp((System.currentTimeMillis())))
                .userId(1)
                .rsEventId(1)
                .build();
        ResponseEntity<VoteResponse<VoteRequest>> vote = voteService.createVote(voteRequest);
        assertEquals(201, vote.getStatusCodeValue());
        assertEquals("create vote success!", Objects.requireNonNull(vote.getBody()).getMessage());
    }

    @Test
    void should_throw_bad_request_when_create_vote_by_error_user_id() {
        VoteRequest voteRequest = VoteRequest.builder()
                .voteNum(1)
                .voteTime(new Timestamp((System.currentTimeMillis())))
                .userId(3123)
                .rsEventId(1)
                .build();
        BaseUserException baseUserException = assertThrows(BaseUserException.class, () -> voteService.createVote(voteRequest));
        assertEquals("this user is not existed!", baseUserException.getMessage());
    }

    @Test
    void should_throw_bad_request_when_create_vote_by_error_event_id() {
        VoteRequest voteRequest = VoteRequest.builder()
                .voteNum(1)
                .voteTime(new Timestamp((System.currentTimeMillis())))
                .userId(1)
                .rsEventId(231)
                .build();
        BaseRsListException baseRsListException = assertThrows(BaseRsListException.class, () -> voteService.createVote(voteRequest));
        assertEquals("this event is not existed!", baseRsListException.getMessage());
    }

    @Test
    void should_throw_bad_request_when_create_vote_and_user_has_vote_num_less_than_vote_num() {
        VoteRequest voteRequest = VoteRequest.builder()
                .voteNum(11)
                .voteTime(new Timestamp((System.currentTimeMillis())))
                .userId(1)
                .rsEventId(1)
                .build();
        BaseVoteException baseVoteException = assertThrows(BaseVoteException.class, () -> voteService.createVote(voteRequest));
        assertEquals("insufficient votes of this user!", baseVoteException.getMessage());
    }

    @Test
    void should_return_votes_info_when_get_vote_by_timestamp_between() {
        long start = (System.currentTimeMillis()) - 40000;
        long end = (System.currentTimeMillis()) + 40000;
        ResponseEntity<VoteResponse<List<VoteRequest>>> votesByTimeRange = voteService.getVotesByTimeRange(start, end);
        assertEquals("get votes success!", Objects.requireNonNull(votesByTimeRange.getBody()).getMessage());
    }

}
