package com.utcn.assignment1.controller;

import com.utcn.assignment1.model.Timestamp;
import com.utcn.assignment1.service.TimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/timestamps")
public class TimestampController {

    @Autowired
    private final TimestampService timestampService;

    @GetMapping(value = "/byUser/{username}/{deviceId}")
    public ResponseEntity<List<Timestamp>> getTimestampsByUser(@PathVariable String username, @PathVariable Long deviceId) {
        return ResponseEntity.ok().body(timestampService.getByUser(username, deviceId));
    }
}
