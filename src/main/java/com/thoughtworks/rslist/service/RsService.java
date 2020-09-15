package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RsService {

    static List<RsEvent> tempRsList = initRsList();

    private static List<RsEvent> initRsList() {
        List<RsEvent> rsList = new ArrayList<>();
        rsList.add(new RsEvent("第一条事件","无分类"));
        rsList.add(new RsEvent("第二条事件","无分类"));
        rsList.add(new RsEvent("第三条事件","无分类"));
        return rsList;
    }


    public RsEventResponse<List<RsEvent>> getRsList(Integer start, Integer end) {
        RsEventResponse<List<RsEvent>> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(200);

        if (start == null || end == null) {
            rsListResponse.setMessage("get all rs list success!");
            rsListResponse.setData(tempRsList);
        }else {
            rsListResponse.setMessage("get rs list in range success!");
            rsListResponse.setData(tempRsList.subList(start - 1, end));
        }
        return rsListResponse;
    }

    public RsEventResponse<RsEvent> getRsListByEventId(Integer eventId) {
        RsEventResponse<RsEvent> reListResponse = new RsEventResponse<>();
        reListResponse.setCode(200);
        reListResponse.setMessage("get rs list by id success!");
        reListResponse.setData(tempRsList.get(eventId - 1));

        return reListResponse;
    }

    public RsEventResponse<RsEvent> createRsList(RsEvent rsEvent) {

        tempRsList.add(rsEvent);
        RsEventResponse<RsEvent> rsListResponse = new RsEventResponse<>();
        rsListResponse.setCode(201);
        rsListResponse.setMessage("create rs list success!");
        rsListResponse.setData(rsEvent);

        return rsListResponse;
    }
}
