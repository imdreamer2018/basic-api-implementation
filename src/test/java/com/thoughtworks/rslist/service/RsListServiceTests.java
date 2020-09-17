package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.entity.RsEventEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RsListServiceTests {


    @Autowired
    RsListService rsListService;

    UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");

    @Test
    void should_return_all_rs_list_json_when_get_rs_list() {
        ResponseEntity<RsEventResponse<List<RsEventEntity>>> response = rsListService.getRsList(null, null);
        assertEquals("get all rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }


    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id_can_not_found() {
        ResponseEntity<RsEventResponse<RsEventEntity>> response = rsListService.getRsListByEventId(1);
        assertEquals("can not found this rs event!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id() {
        ResponseEntity<RsEventResponse<RsEventEntity>> response = rsListService.getRsListByEventId(1);
        assertEquals("get all rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", 1);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.createRsList(rsEventRequest);
        assertEquals("create rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list_and_user_is_not_existed() {
        UserRequest userRequest = new UserRequest("21321312",18,"male","qian.yang@twu.com","17607114747");
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", 1);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.createRsList(rsEventRequest);
        assertEquals("create rs list and user success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_update_rs_list_by_event_id() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", 1);
        RsEventResponse<RsEventRequest> response = rsListService.updateRsListByEventId(1, rsEventRequest);
        assertEquals("update rs list by event id success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_delete_rs_list_by_event_id() {
        RsEventResponse<RsEventRequest> response = rsListService.deleteRsLIstByEventId(1);
        assertEquals("delete rs list by event id success!", response.getMessage());
    }
}
