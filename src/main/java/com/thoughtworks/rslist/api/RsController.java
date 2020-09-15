package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping(path = "/rs")
@RestController
public class RsController {

    @Autowired
    RsService rsService;

    private List<String> rsList = Arrays.asList("第一条事件", "第二条事件", "第三条事件");


    @GetMapping("/list")
    @ResponseBody
    public RsEventResponse<List<RsEvent>> getRsList(@RequestParam(required = false) Integer start,
                                                    @RequestParam(required = false) Integer end) {
        return rsService.getRsList(start, end);
    }

    @GetMapping("/list/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEvent> getRsListByEventId(@PathVariable Integer eventId) {
        return rsService.getRsListByEventId(eventId);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public RsEventResponse<RsEvent> createRsList(@RequestBody RsEvent rsEvent) {
        return rsService.createRsList(rsEvent);
    }

    @PutMapping("/list/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEvent> updateRsList(@PathVariable Integer eventId, @RequestBody RsEvent rsEvent) {
        return rsService.updateRsList(eventId, rsEvent);
    }
}
