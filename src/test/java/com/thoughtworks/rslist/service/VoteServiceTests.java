package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
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

    @Test
    void should_return_all_votes_when_get_all_votes() {

        ResponseEntity<VoteResponse<List<VoteRequest>>> votes = voteService.getVotes();
        assertEquals(200, votes.getStatusCodeValue());
        assertEquals("get votes success!", Objects.requireNonNull(votes.getBody()).getMessage());
    }

}
