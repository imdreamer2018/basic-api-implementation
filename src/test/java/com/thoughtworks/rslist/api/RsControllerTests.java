package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.entity.RsEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_return_status_ok_when_get_rs_list() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list?start=1&end=2"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_ok_when_get_rs_list_by_event_id() throws Exception {
        mockMvc.perform(get("/rs/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_create_when_create_rs_list() throws Exception {
        RsEvent rsEvent = new RsEvent("猪肉涨价啦","经济");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEvent);
        mockMvc.perform(post("/rs/list")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_status_ok_when_create_rs_list() throws Exception {
        RsEvent rsEvent = new RsEvent("猪肉涨价啦","经济");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rsEvent);
        mockMvc.perform(put("/rs/list/1")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_ok_when_delete_rs_list() throws Exception {
        mockMvc.perform(delete("/rs/list/1"))
                .andExpect(status().isOk());
    }



}
