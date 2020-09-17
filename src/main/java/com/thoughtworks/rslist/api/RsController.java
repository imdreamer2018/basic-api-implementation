package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/rs")
@RestController
public class RsController {

    @Autowired
    RsService rsService;


    @GetMapping("/lists")
    @ResponseStatus()
    @ResponseBody
    public ResponseEntity getRsList(@RequestParam(required = false) Integer start,
                                                                    @RequestParam(required = false) Integer end) {
        return rsService.getRsList(start, end);
    }

    @GetMapping("/lists/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEventRequest> getRsListByEventId(@PathVariable Integer eventId) {
        return rsService.getRsListByEventId(eventId);
    }

    @PostMapping("/lists")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<RsEventResponse<RsEventRequest>> createRsList(@Validated @RequestBody RsEventRequest rsEventRequest) {
        return rsService.createRsList(rsEventRequest);
    }

    @PutMapping("/lists/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEventRequest> updateRsList(@PathVariable Integer eventId, @RequestBody RsEventRequest rsEventRequest) {
        return rsService.updateRsListByEventId(eventId, rsEventRequest);
    }

    @DeleteMapping("/lists/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEventRequest> deleleRsList(@PathVariable Integer eventId) {
        return rsService.deleteRsLIstByEventId(eventId);
    }
}
