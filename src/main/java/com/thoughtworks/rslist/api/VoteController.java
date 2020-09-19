package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping(path = "/rs")
@RestController
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping("/votes")
    public ResponseEntity<VoteResponse<List<VoteRequest>>> getVotes() {
        return voteService.getVotes();
    }

    @GetMapping("/votes/{voteId}")
    public ResponseEntity<VoteResponse<VoteRequest>> getVoteByVoteId(@PathVariable Integer voteId) {
        return voteService.getVoteByVoteId(voteId);
    }

    @PostMapping("/votes")
    public ResponseEntity createVote(@Validated @RequestBody VoteRequest voteRequest) {
        return voteService.createVote(voteRequest);
    }

    @GetMapping("/votes/range")
    public ResponseEntity getVotesByTimeRange(@RequestParam long startTime,
                                              @RequestParam long endTime) {
        return voteService.getVotesByTimeRange(startTime, endTime);
    }
}
