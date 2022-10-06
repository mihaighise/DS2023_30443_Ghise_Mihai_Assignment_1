package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;

import java.util.List;

public interface IDeviceService {
    List<Device> getDevices();
    Device addDevice(Device device);
    User assignDeviceToUser(User user, Device device);
    List<Device> getFreeDevices();
    void deleteDevice(Long id);
    List<Device> getDevicesByUser(String username);
}
