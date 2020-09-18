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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RsEventRepository rsEventRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RsEventRepository rsEventRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.rsEventRepository = rsEventRepository;
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

    public ResponseEntity<VoteResponse<VoteRequest>> createVote(VoteRequest voteRequest) {
        Optional<UserEntity> user = userRepository.findById(voteRequest.getUserId());
        if (!user.isPresent())
            throw new BaseUserException("this user is not existed!");
        Optional<RsEventEntity> event = rsEventRepository.findById(voteRequest.getRsEventId());
        if (!event.isPresent())
            throw new BaseRsListException("this event is not existed!");
        if (user.get().getVoteNum() < voteRequest.getVoteNum())
            throw new BaseVoteException("insufficient votes of this user!");
        VoteResponse<VoteRequest> voteResponse = new VoteResponse<>();
        VoteEntity voteEntity = VoteEntity.builder()
                .voteNum(voteRequest.getVoteNum())
                .voteTime(getNowTime())
                .user(user.get())
                .rsEvent(event.get())
                .build();
        voteRepository.save(voteEntity);
        UserEntity userEntity = user.get();
        userEntity.setVoteNum(userEntity.getVoteNum() - voteRequest.getVoteNum());
        userRepository.save(userEntity);
        RsEventEntity rsEventEntity = event.get();
        rsEventEntity.setVoteNum(rsEventEntity.getVoteNum() + voteRequest.getVoteNum());
        rsEventRepository.save(rsEventEntity);
        voteResponse.setCode(201);
        voteResponse.setMessage("create vote success!");
        voteResponse.setData(entityToRequest(voteEntity));
        return ResponseEntity.created(URI.create("/rs/votes")).body(voteResponse);
    }

    private String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return sdf.format(new Date());
    }
}
