package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.Timestamp;
import com.utcn.assignment1.model.User;

import java.util.List;

public interface ITimestampService {
    List<Timestamp> getByUser(String username, Long deviceId);
    List<Timestamp> getByDevice(Device device);
}
