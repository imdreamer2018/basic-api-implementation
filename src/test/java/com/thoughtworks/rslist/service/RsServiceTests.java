package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import com.thoughtworks.rslist.entity.User;
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

    User user = new User("yangqian",18,"male","qian.yang@twu.com","17607114747");

    @Test
    void should_return_all_rs_list_json_when_get_rs_list() {
        RsEventResponse<List<RsEvent>> response = rsService.getRsList(null, null);
        assertEquals("get all rs list success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_in_range_json_when_get_rs_list() {
        RsEventResponse<List<RsEvent>> response = rsService.getRsList(1, 2);
        assertEquals("get rs list in range success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id() {
        RsEventResponse<RsEvent> response = rsService.getRsListByEventId(1);
        assertEquals("get rs list by id success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list() {
        RsEvent rsEvent = new RsEvent("猪肉涨价啦","经济", user);
        ResponseEntity<RsEventResponse<RsEvent>> response = rsService.createRsList(rsEvent);
        assertEquals("create rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list_and_user_is_not_existed() {
        User user = new User("21321312",18,"male","qian.yang@twu.com","17607114747");
        RsEvent rsEvent = new RsEvent("猪肉涨价啦","经济", user);
        ResponseEntity<RsEventResponse<RsEvent>> response = rsService.createRsList(rsEvent);
        assertEquals("create rs list and user success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_update_rs_list_by_event_id() {
        RsEvent rsEvent = new RsEvent("猪肉涨价啦","经济", user);
        RsEventResponse<RsEvent> response = rsService.updateRsListByEventId(1, rsEvent);
        assertEquals("update rs list by event id success!", response.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_delete_rs_list_by_event_id() {
        RsEventResponse<RsEvent> response = rsService.deleteRsLIstByEventId(1);
        assertEquals("delete rs list by event id success!", response.getMessage());
    }
}
