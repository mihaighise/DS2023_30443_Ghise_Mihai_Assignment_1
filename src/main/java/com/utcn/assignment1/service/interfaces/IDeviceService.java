package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.DeviceDTO;

import java.util.List;

public interface IDeviceService {
    List<Device> getDevices();
    Device addDevice(DeviceDTO deviceDTO);
    User assignDeviceToUser(User user, Device device);
    List<Device> getFreeDevices();
    void deleteDevice(Long id);
    List<Device> getDevicesByUser(String username);
}
