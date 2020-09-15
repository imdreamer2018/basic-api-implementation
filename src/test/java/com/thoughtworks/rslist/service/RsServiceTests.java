package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RsServiceTests {


    @Autowired
    RsService rsService;

    @Test
    void should_return_rsList_json_when_get_rs_list() throws Exception {
        RsEventResponse<List<RsEvent>> response = rsService.getAllList();
        assertEquals("get all rs list success!", response.getMessage());
    }
}
