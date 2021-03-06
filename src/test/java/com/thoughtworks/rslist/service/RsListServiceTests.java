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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RsListServiceTests {


    @Autowired
    RsListService rsListService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RsEventRepository rsEventRepository;

    UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");


    @BeforeEach
    void setUp() {
        UserEntity userEntity = UserEntity.builder()
                .userName("yangqian")
                .age(18)
                .gender("male")
                .email("yq@twu.com")
                .phone("17607114747")
                .build();
        userRepository.save(userEntity);

        RsEventEntity rsEventEntity = RsEventEntity.builder()
                .eventName("猪肉涨价啦")
                .keyWord("经济")
                .voteNum(0)
                .user(userEntity)
                .build();
        rsEventRepository.save(rsEventEntity);
    }

//    @AfterEach
//    void endUp() {
//        rsEventRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void should_return_all_rs_list_json_when_get_rs_list() {
        ResponseEntity<RsEventResponse<List<RsEventRequest>>> response = rsListService.getRsList(null, null);
        assertEquals("get all rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }


    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id_can_not_found() {
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.getRsListByEventId(1);
        assertEquals("can not found this rs event!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_get_rs_list_by_event_id() {
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.getRsListByEventId(1);
        assertEquals("get all rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦111","经济", 1);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.createRsList(rsEventRequest);
        assertEquals("create rs list success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_create_rs_list_and_user_is_not_existed() {
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", 1);
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.createRsList(rsEventRequest);
        assertEquals("create rs list and user success!", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void should_return_rs_list_json_when_update_rs_list_by_event_id() {
        RsEventRequest rsEventRequest = RsEventRequest.builder()
                .eventName("猪肉涨价啦")
                .keyWord("hhh")
                .userId(1)
                .build();
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.updateRsListByEventId(1, rsEventRequest);
        assertEquals("update rs list by event id success!", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals("hhh", response.getBody().getData().getKeyWord());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void should_throw_FORBDDIN_when_update_rs_list_by_event_id_and_user_not_have_permission() {
        RsEventRequest rsEventRequest = RsEventRequest.builder()
                .eventName("猪肉涨价啦")
                .keyWord("hhh")
                .userId(2)
                .build();

        UnAuthenticatedException exception = assertThrows(UnAuthenticatedException.class, () -> rsListService.updateRsListByEventId(1, rsEventRequest));
        assertEquals("FORBIDDEN", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_update_rs_list_by_event_id_and_can_not_found_this_event() {
        RsEventRequest rsEventRequest = RsEventRequest.builder()
                .eventName("猪肉涨价啦")
                .keyWord("hhh")
                .userId(1)
                .build();

        BaseRsListException exception = assertThrows(BaseRsListException.class, () -> rsListService.updateRsListByEventId(5, rsEventRequest));
        assertEquals("can not found this event!", exception.getMessage());
    }

    @Test
    void should_return_rs_list_json_when_delete_rs_list_by_event_id() {
        ResponseEntity<RsEventResponse<RsEventRequest>> response = rsListService.deleteRsLIstByEventId(1);
        assertEquals("delete rs list by event id success!", Objects.requireNonNull(response.getBody()).getMessage());
    }
}
