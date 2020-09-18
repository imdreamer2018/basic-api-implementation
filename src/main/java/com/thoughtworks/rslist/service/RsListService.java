package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.exception.BaseRsListException;
import com.thoughtworks.rslist.exception.UnAuthenticatedException;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RsListService {

    @Autowired
    UserService userService;

    @Autowired
    RsEventRepository rsEventRepository;

    @Autowired
    UserRepository userRepository;


    public ResponseEntity<RsEventResponse<List<RsEventRequest>>> getRsList(Pageable pageable) {
        RsEventResponse<List<RsEventRequest>> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        Page<RsEventEntity> eventEntityList = rsEventRepository.findAll(pageable);
        rsListResponse.setMessage("get all rs list success!");
        rsListResponse.setData(eventEntityList.stream()
                .map(RsListService::from).collect(Collectors.toList()));
        return ResponseEntity.ok().body(rsListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> getRsListByEventId(Integer eventId) {
        RsEventResponse<RsEventRequest> reListResponse = new RsEventResponse<>();
        Optional<RsEventEntity> eventEntity = rsEventRepository.findById(eventId);
        if (!eventEntity.isPresent()) {
            throw new BaseRsListException("invalid id");
        }
        reListResponse.setMessage("get rs list by id success!");
        reListResponse.setData(from(eventEntity.get()));

        return ResponseEntity.ok().body(reListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> createRsList(RsEventRequest rsEventRequest) {
        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        Optional<UserEntity> user = userRepository.findById(rsEventRequest.getUserId());
        if (!user.isPresent()) {
            throw new BaseRsListException("user is not existed!");
        }
        RsEventEntity rsEventEntity = RsEventEntity.builder()
                .eventName(rsEventRequest.getEventName())
                .keyWord(rsEventRequest.getKeyWord())
                .voteNum(rsEventRequest.getVoteNum())
                .user(user.get())
                .build();
        rsEventRepository.save(rsEventEntity);
        rsEventRequest.setEventId(rsEventEntity.getId());
        rsListResponse.setCode(201);
        rsListResponse.setMessage("create rs list success!");
        rsListResponse.setData(rsEventRequest);

        return ResponseEntity
                .created(URI.create("/rs/lists"))
                .body(rsListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> updateRsListByEventId(Integer eventId, RsEventRequest rsEventRequest) {

        Optional<RsEventEntity> rsEvent = rsEventRepository.findById(eventId);
        if (!rsEvent.isPresent()) {
            throw new BaseRsListException("can not found this event!");
        }
        if (!rsEvent.get().getId().equals(rsEventRequest.getUserId())) {
            throw new UnAuthenticatedException("FORBIDDEN");
        }
        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("update rs list by event id success!");
        if (!rsEventRequest.getEventName().isEmpty())
            rsEvent.get().setEventName(rsEventRequest.getEventName());
        if (!rsEventRequest.getKeyWord().isEmpty())
            rsEvent.get().setKeyWord(rsEventRequest.getKeyWord());
        rsListResponse.setData(from(rsEvent.get()));
        rsEventRepository.save(rsEvent.get());
        return ResponseEntity
                .ok(rsListResponse);

    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> deleteRsLIstByEventId(Integer eventId) {
        Optional<RsEventEntity> rsEvent = rsEventRepository.findById(eventId);
        if (!rsEvent.isPresent()) {
            throw new BaseRsListException("can not found this event!");
        }
        rsEventRepository.deleteById(eventId);
        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("delete rs list by event id success!");
        rsListResponse.setData(from(rsEvent.get()));

        return ResponseEntity.ok(rsListResponse);
    }


    private static RsEventRequest from(RsEventEntity rsEventEntity) {
        return RsEventRequest.builder()
                .eventId(rsEventEntity.getId())
                .userId(rsEventEntity.getUser().getId())
                .keyWord(rsEventEntity.getKeyWord())
                .eventName(rsEventEntity.getEventName())
                .voteNum(rsEventEntity.getVoteNum())
                .build();
    }


}
