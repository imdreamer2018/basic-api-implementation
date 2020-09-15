package com.thoughtworks.rslist.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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



}
