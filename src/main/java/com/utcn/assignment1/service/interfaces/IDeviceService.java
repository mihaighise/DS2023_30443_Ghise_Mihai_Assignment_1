package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.DeviceDTO;

import java.util.List;

public interface IDeviceService {
    List<DeviceDTO> getDevices();
    DeviceDTO addDevice(DeviceDTO deviceDTO);
    User assignDeviceToUser(User user, Device device);
    List<DeviceDTO> getFreeDevices();
    void deleteDevice(Long id);
    List<DeviceDTO> getDevicesByUser(String username);
    DeviceDTO updateDevice(Long oldDeviceId, DeviceDTO newDevice);
}
