package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class VoteServiceTests {


    @Autowired
    VoteService voteService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RsEventRepository rsEventRepository;

    @BeforeEach
    void setUp() {
        UserEntity user = UserEntity.builder()
                .userName("123")
                .age(18)
                .gender("male")
                .email("123@123.com")
                .phone("17607114747")
                .build();
        userRepository.save(user);

        RsEventEntity rsEventEntity = RsEventEntity.builder()
                .keyWord("经济")
                .eventName("猪肉")
                .user(user)
                .build();
        rsEventRepository.save(rsEventEntity);
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
        assertEquals("get vote success!", vote.getBody().getMessage());
    }

}
