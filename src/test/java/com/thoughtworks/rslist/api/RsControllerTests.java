package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.service.RsListService;
import com.thoughtworks.rslist.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    RsEventRepository rsEventRepository;

    @Autowired
    UserRepository userRepository;



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

    @AfterEach
    void endUp() {
        rsEventRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void should_return_status_ok_when_get_rs_list() throws Exception {
        mockMvc.perform(get("/rs/lists"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/lists?start=1&end=2"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_ok_when_get_rs_list_by_event_id() throws Exception {
        mockMvc.perform(get("/rs/lists/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_create_when_create_rs_list() throws Exception {

        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦11","经济", 1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEventRequest);
        mockMvc.perform(post("/rs/lists")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_bad_request_when_create_rs_list_user_is_not_existed() throws Exception {

        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", 1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEventRequest);
        mockMvc.perform(post("/rs/lists")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_return_status_ok_when_update_rs_list() throws Exception {
        RsEventRequest rsEventRequest = RsEventRequest.builder()
                .eventName("猪肉涨价啦")
                .keyWord("hhh")
                .userId(1)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEventRequest);
        mockMvc.perform(put("/rs/lists/1")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_ok_when_delete_rs_list() throws Exception {
        mockMvc.perform(delete("/rs/lists/1"))
                .andExpect(status().isOk());
    }



}
