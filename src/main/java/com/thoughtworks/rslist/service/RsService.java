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


    public RsEventResponse<List<RsEvent>> getAllList() {

        RsEventResponse<List<RsEvent>> rsEventRsEventResponse = new RsEventResponse<>();
        rsEventRsEventResponse.setCode(200);
        rsEventRsEventResponse.setMessage("get all rs list success!");
        rsEventRsEventResponse.setData(tempRsList);

        return rsEventRsEventResponse;
    }

    public RsEventResponse<RsEvent> getListById(Integer eventId) {

        RsEventResponse<RsEvent> rsEventRsEventResponse = new RsEventResponse<>();
        rsEventRsEventResponse.setCode(200);
        rsEventRsEventResponse.setMessage("get rs list by id success!");
        rsEventRsEventResponse.setData(tempRsList.get(eventId));

        return rsEventRsEventResponse;
    }
}
