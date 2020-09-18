package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
