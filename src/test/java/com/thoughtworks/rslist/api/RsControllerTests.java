package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.dto.UserRequest;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.service.RsListService;
import com.thoughtworks.rslist.service.UserService;
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
        UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", userRequest);

        userService.registerUser(userRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEventRequest);
        mockMvc.perform(post("/rs/lists")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }



    @Test
    void should_return_status_ok_when_update_rs_list() throws Exception {
        UserRequest userRequest = new UserRequest("yangqian",18,"male","qian.yang@twu.com","17607114747");
        RsEventRequest rsEventRequest = new RsEventRequest("猪肉涨价啦","经济", userRequest);
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
