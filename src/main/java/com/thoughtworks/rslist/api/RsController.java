package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.dto.RsEventRequest;
import com.thoughtworks.rslist.service.RsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/rs")
@RestController
public class RsController {

    @Autowired
    RsListService rsListService;


    @GetMapping("/lists")
    @ResponseStatus()
    @ResponseBody
    public ResponseEntity getRsList(@RequestParam(required = false) Integer start,
                                    @RequestParam(required = false) Integer end) {
        return rsListService.getRsList(start, end);
    }

    @GetMapping("/lists/{eventId}")
    @ResponseBody
    public ResponseEntity getRsListByEventId(@PathVariable Integer eventId) {
        return rsListService.getRsListByEventId(eventId);
    }

    @PostMapping("/lists")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<RsEventResponse<RsEventRequest>> createRsList(@Validated @RequestBody RsEventRequest rsEventRequest) {
        return rsListService.createRsList(rsEventRequest);
    }

    @PutMapping("/lists/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEventRequest> updateRsList(@PathVariable Integer eventId, @RequestBody RsEventRequest rsEventRequest) {
        return rsListService.updateRsListByEventId(eventId, rsEventRequest);
    }

    @DeleteMapping("/lists/{eventId}")
    @ResponseBody
    public RsEventResponse<RsEventRequest> deleleRsList(@PathVariable Integer eventId) {
        return rsListService.deleteRsLIstByEventId(eventId);
    }
}
