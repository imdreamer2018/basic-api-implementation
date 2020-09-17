package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.exception.BaseRsListException;
import com.thoughtworks.rslist.exception.UnAuthenticatedException;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.rslist.service.UserService.userRequestList;

@Service
public class RsListService {

    @Autowired
    UserService userService;

    @Autowired
    RsEventRepository rsEventRepository;

    @Autowired
    UserRepository userRepository;
    static List<RsEventRequest> tempRsList = new ArrayList<>();



    public ResponseEntity<RsEventResponse<List<RsEventEntity>>> getRsList(Integer start, Integer end) {
        RsEventResponse<List<RsEventEntity>> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        List<RsEventEntity> eventEntityList = rsEventRepository.findAll();

        if (start == null || end == null) {
            rsListResponse.setMessage("get all rs list success!");
            rsListResponse.setData(eventEntityList);
        }else {
            verifyEventId(start, end);
            rsListResponse.setMessage("get rs list in range success!");
            rsListResponse.setData(eventEntityList.subList(start - 1, end));
        }
        return ResponseEntity.ok().body(rsListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventEntity>> getRsListByEventId(Integer eventId) {
        RsEventResponse<RsEventEntity> reListResponse = new RsEventResponse<>();
        Optional<RsEventEntity> eventEntity = rsEventRepository.findById(eventId);
        if (!eventEntity.isPresent()) {
            throw new BaseRsListException("invalid id");
        }
        reListResponse.setMessage("get rs list by id success!");
        reListResponse.setData(eventEntity.get());

        return ResponseEntity.ok().body(reListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> createRsList(RsEventRequest rsEventRequest) {
        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        Optional<UserEntity> user = userRepository.findById(rsEventRequest.getUserId());
        if (!user.isPresent()) {
            throw new BaseRsListException("user is not existed!");
        }
        rsEventRepository.save(RsEventEntity.builder()
                .eventName(rsEventRequest.getEventName())
                .keyWord(rsEventRequest.getKeyWord())
                .user(user.get())
                .build());

        rsListResponse.setCode(201);
        rsListResponse.setMessage("create rs list success!");
        rsListResponse.setData(rsEventRequest);

        return ResponseEntity
                .created(URI.create("/rs/lists"))
                .body(rsListResponse);
    }

    public ResponseEntity<RsEventResponse<RsEventEntity>> updateRsListByEventId(Integer eventId, RsEventRequest rsEventRequest) {

        Optional<RsEventEntity> rsEvent = rsEventRepository.findById(eventId);
        if (!rsEvent.isPresent()) {
            throw new BaseRsListException("can not found this event!");
        }
        if (!rsEvent.get().getId().equals(rsEventRequest.getUserId())) {
            throw new UnAuthenticatedException("FORBIDDEN");
        }
        RsEventResponse<RsEventEntity> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("update rs list by event id success!");
        if (!rsEventRequest.getEventName().isEmpty())
            rsEvent.get().setEventName(rsEventRequest.getEventName());
        if (!rsEventRequest.getKeyWord().isEmpty())
            rsEvent.get().setKeyWord(rsEventRequest.getKeyWord());
        rsListResponse.setData(rsEvent.get());

        return ResponseEntity
                .ok(rsListResponse);

    }

    public RsEventResponse<RsEventRequest> deleteRsLIstByEventId(Integer eventId) {
        verifyEventId(eventId);
        RsEventRequest currentRsEventRequest = tempRsList.get(eventId - 1);
        tempRsList.remove(eventId - 1);

        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("delete rs list by event id success!");
        rsListResponse.setData(currentRsEventRequest);

        return rsListResponse;
    }

    private void verifyEventId(Integer eventId) {
        if (eventId <= 0 || eventId > tempRsList.size()) {
            throw new BaseRsListException("event id is invalid input cause null point exception");
        }
    }

    private void verifyEventId(Integer startEventId, Integer endEventId) {
        if (startEventId > endEventId || startEventId <= 0 || startEventId > tempRsList.size() || endEventId > tempRsList.size()) {
            throw new BaseRsListException("invalid input cause null point exception");
        }
    }


}
