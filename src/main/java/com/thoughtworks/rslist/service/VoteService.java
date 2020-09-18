package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.entity.VoteEntity;
import com.thoughtworks.rslist.repository.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public ResponseEntity<VoteResponse<List<VoteRequest>>> getVotes() {

        List<VoteEntity> votes = voteRepository.findAll();
        VoteResponse<List<VoteRequest>> voteResponse = new VoteResponse<>();
        voteResponse.setCode(200);
        voteResponse.setMessage("get votes success!");
        voteResponse.setData(votes.stream()
                .map(VoteService::EntityToRequest)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(voteResponse);
    }

    private static VoteRequest EntityToRequest(VoteEntity voteEntity) {
        return VoteRequest.builder()
                .id(voteEntity.getId())
                .voteNum(voteEntity.getVoteNum())
                .voteTime(voteEntity.getVoteTime())
                .userId(voteEntity.getUser().getId())
                .rsEventId(voteEntity.getRsEvent().getId())
                .build();
    }
}
