package com.utcn.assignment1.controller;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices")
public class DeviceController {

    @Autowired
    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok().body(deviceService.getDevices());
    }

    @GetMapping(value = "/free")
    public ResponseEntity<List<Device>> getFreeDevices() {
        return ResponseEntity.ok().body(deviceService.getFreeDevices());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }
}
