package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RsServiceTests {


    @Autowired
    RsService rsService;

    UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");

    @Test
    void should_return_all_rs_list_json_when_get_rs_list() {
        ResponseEntity<RsEventResponse<List<RsEventRequest>>> response = rsService.getRsList(null, null);
        assertEquals("get all rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_in_range_json_when_get_rs_list() {
        ResponseEntity<RsEventResponse<List<RsEventRequest>>> response = rsService.getRsList(1, 2);
        assertEquals("get rs list in range success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id() {
        RsEventResponse<RsEventRequest> response = rsService.getRsListByEventId(1);
        assertEquals("get rs list by id success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", userRequest);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsService.createRsList(rsEventRequest);
        assertEquals("create rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list_and_user_is_not_existed() {
        UserRequest userRequest = new UserRequest("21321312",18,"male","qian.yang@twu.com","17607114747");
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", userRequest);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsService.createRsList(rsEventRequest);
        assertEquals("create rs list and user success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_update_rs_list_by_event_id() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", userRequest);
        RsEventResponse<RsEventRequest> response = rsService.updateRsListByEventId(1, rsEventRequest);
        assertEquals("update rs list by event id success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_delete_rs_list_by_event_id() {
        RsEventResponse<RsEventRequest> response = rsService.deleteRsLIstByEventId(1);
        assertEquals("delete rs list by event id success!", response.getMessage());
    }
}
