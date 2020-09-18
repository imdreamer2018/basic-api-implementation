package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.VoteRequest;
import com.thoughtworks.rslist.dto.VoteResponse;
import com.thoughtworks.rslist.entity.VoteEntity;
import com.thoughtworks.rslist.exception.BaseVoteException;
import com.thoughtworks.rslist.repository.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(VoteService::entityToRequest)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(voteResponse);
    }

    private static VoteRequest entityToRequest(VoteEntity voteEntity) {
        return VoteRequest.builder()
                .id(voteEntity.getId())
                .voteNum(voteEntity.getVoteNum())
                .voteTime(voteEntity.getVoteTime())
                .userId(voteEntity.getUser().getId())
                .rsEventId(voteEntity.getRsEvent().getId())
                .build();
    }

    public ResponseEntity<VoteResponse<VoteRequest>> getVoteByVoteId(Integer voteId) {
        Optional<VoteEntity> vote = voteRepository.findById(voteId);
        if (!vote.isPresent()) {
            throw new BaseVoteException("can not find this vote!");
        }
        VoteResponse<VoteRequest> voteResponse = new VoteResponse<>();
        voteResponse.setCode(200);
        voteResponse.setMessage("get vote success!");
        voteResponse.setData(entityToRequest(vote.get()));

        return ResponseEntity.ok(voteResponse);
    }
}
