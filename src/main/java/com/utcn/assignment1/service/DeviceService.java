package com.utcn.assignment1.service;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.repository.DeviceRepository;
import com.utcn.assignment1.repository.UserRepository;
import com.utcn.assignment1.service.interfaces.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService implements IDeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public User assignDeviceToUser(User user, Device device) {
        user.addDevice(device);
        return userRepository.save(user);
    }
}
