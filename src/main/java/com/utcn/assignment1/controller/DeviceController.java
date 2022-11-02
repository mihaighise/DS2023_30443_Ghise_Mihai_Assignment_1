package com.utcn.assignment1.controller;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.DeviceDTO;
import com.utcn.assignment1.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        return ResponseEntity.ok().body(deviceService.getDevices());
    }

    @GetMapping(value = "/byUser/{username}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByUser(@PathVariable String username) {
        return ResponseEntity.ok().body(deviceService.getDevicesByUser(username));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DeviceDTO> addDevice(@RequestBody DeviceDTO deviceDTO) {
        return ResponseEntity.ok().body(deviceService.addDevice(deviceDTO));
    }

    @GetMapping(value = "/free")
    public ResponseEntity<List<DeviceDTO>> getFreeDevices() {
        return ResponseEntity.ok().body(deviceService.getFreeDevices());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }

    @PutMapping(value = "/update/{oldDeviceId}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long oldDeviceId, @RequestBody DeviceDTO newDevice) {
        return ResponseEntity.ok().body(deviceService.updateDevice(oldDeviceId, newDevice));
    }
}
