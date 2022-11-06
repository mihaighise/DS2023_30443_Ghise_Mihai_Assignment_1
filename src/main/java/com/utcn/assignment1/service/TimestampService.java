package com.utcn.assignment1.service;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.Timestamp;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.repository.TimestampRepository;
import com.utcn.assignment1.repository.UserRepository;
import com.utcn.assignment1.service.interfaces.ITimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TimestampService implements ITimestampService {

    @Autowired
    private final TimestampRepository timestampRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Timestamp> getByUser(String username, Long deviceId) {
        User user = userRepository.findByUsername(username);
        List<Timestamp> result = new ArrayList<>();
        user.getDevices().forEach(device -> {
            if(device.getId().equals(deviceId)) {
                result.addAll(timestampRepository.findByDevice(device));
            }
        });
        return result;
    }

    @Override
    public List<Timestamp> getByDevice(Device device) {
        return null;
    }
}
