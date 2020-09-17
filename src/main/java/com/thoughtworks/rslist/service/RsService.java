package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.exception.BaseRsListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.rslist.service.UserService.userRequestList;

@Service
public class RsService {

    @Autowired
    UserService userService;

    static List<RsEventRequest> tempRsList = initRsList();

    private static List<RsEventRequest> initRsList() {
        List<RsEventRequest> rsList = new ArrayList<>();
        UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");
        rsList.add(new RsEventRequest("第一条事件","无分类", userRequest));
        rsList.add(new RsEventRequest("第二条事件","无分类", userRequest));
        rsList.add(new RsEventRequest("第三条事件","无分类", userRequest));
        return rsList;
    }


    public ResponseEntity<RsEventResponse<List<RsEventRequest>>> getRsList(Integer start, Integer end) {
        RsEventResponse<List<RsEventRequest>> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);

        if (start == null || end == null) {
            rsListResponse.setMessage("get all rs list success!");
            rsListResponse.setData(tempRsList);
        }else {
            verifyEventId(start, end);
            rsListResponse.setMessage("get rs list in range success!");
            rsListResponse.setData(tempRsList.subList(start - 1, end));
        }
        return ResponseEntity.ok().body(rsListResponse);
    }

    public RsEventResponse<RsEventRequest> getRsListByEventId(Integer eventId) {
        verifyEventId(eventId);
        RsEventResponse<RsEventRequest> reListResponse = new RsEventResponse<>();
        reListResponse.setCode(200);
        reListResponse.setMessage("get rs list by id success!");
        reListResponse.setData(tempRsList.get(eventId - 1));

        return reListResponse;
    }

    public ResponseEntity<RsEventResponse<RsEventRequest>> createRsList(RsEventRequest rsEventRequest) {
        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        if (userService.verifyUserIsExited(rsEventRequest.getUserRequest().getUserName())) {
            tempRsList.add(rsEventRequest);
            rsListResponse.setMessage("create rs list success!");
        } else {
            userRequestList.add(rsEventRequest.getUserRequest());
            tempRsList.add(rsEventRequest);
            rsListResponse.setMessage("create rs list and user success!");
        }
        rsListResponse.setCode(201);
        rsListResponse.setData(rsEventRequest);

        return ResponseEntity
                .created(null)
                .header("index", String.valueOf(tempRsList.size()))
                .body(rsListResponse);
    }

    public RsEventResponse<RsEventRequest> updateRsListByEventId(Integer eventId, RsEventRequest rsEventRequest) {
        verifyEventId(eventId);
        RsEventRequest currentRsEventRequest = tempRsList.get(eventId - 1);
        currentRsEventRequest.setEventName(rsEventRequest.getEventName().isEmpty()? currentRsEventRequest.getEventName(): rsEventRequest.getEventName());
        currentRsEventRequest.setKeyWord(rsEventRequest.getKeyWord().isEmpty()? currentRsEventRequest.getKeyWord(): rsEventRequest.getKeyWord());
        tempRsList.remove(eventId - 1);
        tempRsList.add(eventId - 1, currentRsEventRequest);

        RsEventResponse<RsEventRequest> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("update rs list by event id success!");
        rsListResponse.setData(rsEventRequest);

        return rsListResponse;

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
