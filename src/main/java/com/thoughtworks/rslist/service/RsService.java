package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import com.thoughtworks.rslist.entity.User;
import com.thoughtworks.rslist.exception.BaseRsListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.rslist.service.UserService.userList;

@Service
public class RsService {

    @Autowired
    UserService userService;

    static List<RsEvent> tempRsList = initRsList();

    private static List<RsEvent> initRsList() {
        List<RsEvent> rsList = new ArrayList<>();
        User user = new User("yangqian",18,"male","qian.yang@twu.com","17607114747");
        rsList.add(new RsEvent("第一条事件","无分类", user));
        rsList.add(new RsEvent("第二条事件","无分类", user));
        rsList.add(new RsEvent("第三条事件","无分类", user));
        return rsList;
    }


    public RsEventResponse<List<RsEvent>> getRsList(Integer start, Integer end) {
        RsEventResponse<List<RsEvent>> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);

        if (start == null || end == null) {
            rsListResponse.setMessage("get all rs list success!");
            rsListResponse.setData(tempRsList);
        }else {
            verifyEventId(start, end);
            rsListResponse.setMessage("get rs list in range success!");
            rsListResponse.setData(tempRsList.subList(start - 1, end));
        }
        return rsListResponse;
    }

    public RsEventResponse<RsEvent> getRsListByEventId(Integer eventId) {
        verifyEventId(eventId);
        RsEventResponse<RsEvent> reListResponse = new RsEventResponse<>();
        reListResponse.setCode(200);
        reListResponse.setMessage("get rs list by id success!");
        reListResponse.setData(tempRsList.get(eventId - 1));

        return reListResponse;
    }

    public ResponseEntity<RsEventResponse<RsEvent>> createRsList(RsEvent rsEvent) {
        RsEventResponse<RsEvent> rsListResponse = new RsEventResponse<>();
        if (userService.verifyUserIsExited(rsEvent.getUser().getUserName())) {
            tempRsList.add(rsEvent);
            rsListResponse.setMessage("create rs list success!");
        } else {
            userList.add(rsEvent.getUser());
            tempRsList.add(rsEvent);
            rsListResponse.setMessage("create rs list and user success!");
        }
        rsListResponse.setCode(201);
        rsListResponse.setData(rsEvent);

        return ResponseEntity
                .created(null)
                .header("index", String.valueOf(tempRsList.size()))
                .body(rsListResponse);
    }

    public RsEventResponse<RsEvent> updateRsListByEventId(Integer eventId, RsEvent rsEvent) {
        verifyEventId(eventId);
        RsEvent currentRsEvent = tempRsList.get(eventId - 1);
        currentRsEvent.setEventName(rsEvent.getEventName().isEmpty()?currentRsEvent.getEventName(): rsEvent.getEventName());
        currentRsEvent.setKeyWord(rsEvent.getKeyWord().isEmpty()?currentRsEvent.getKeyWord(): rsEvent.getKeyWord());
        tempRsList.remove(eventId - 1);
        tempRsList.add(eventId - 1, currentRsEvent);

        RsEventResponse<RsEvent> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("update rs list by event id success!");
        rsListResponse.setData(rsEvent);

        return rsListResponse;

    }

    public RsEventResponse<RsEvent> deleteRsLIstByEventId(Integer eventId) {
        verifyEventId(eventId);
        RsEvent currentRsEvent = tempRsList.get(eventId - 1);
        tempRsList.remove(eventId - 1);

        RsEventResponse<RsEvent> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);
        rsListResponse.setMessage("delete rs list by event id success!");
        rsListResponse.setData(currentRsEvent);

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
