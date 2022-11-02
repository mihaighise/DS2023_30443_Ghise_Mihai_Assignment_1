package com.utcn.assignment1.service;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.DeviceDTO;
import com.utcn.assignment1.model.mapper.DeviceMapper;
import com.utcn.assignment1.repository.DeviceRepository;
import com.utcn.assignment1.repository.UserRepository;
import com.utcn.assignment1.service.interfaces.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService implements IDeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final UserRepository userRepository;

    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceDTO> getDevices() {
        return deviceMapper.convertAllToDTO(deviceRepository.findAll());
    }

    @Override
    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = deviceMapper.convertToEntity(deviceDTO);
        Device newSavedDevice = deviceRepository.save(device);
        return deviceMapper.convertToDTO(newSavedDevice);
    }

    @Override
    public User assignDeviceToUser(User user, Device device) {
        user.addDevice(device);
        return userRepository.save(user);
    }

    @Override
    public List<DeviceDTO> getFreeDevices() {
        List<DeviceDTO> freeDevices = new ArrayList<>();
        deviceRepository.findAll().forEach(device -> {
            if(device.getUser() == null) {
                freeDevices.add(deviceMapper.convertToDTO(device));
            }
        });
        return freeDevices;
    }

    @Override
    public void deleteDevice(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        device.ifPresent(deviceRepository::delete);
    }

    @Override
    public List<DeviceDTO> getDevicesByUser(String username) {
        User user = userRepository.findByUsername(username);
        List<DeviceDTO> usersDevices = new ArrayList<>();
        deviceRepository.findAll().forEach(device -> {
            if(device.getUser() == user) {
                usersDevices.add(deviceMapper.convertToDTO(device));
            }
        });
        return usersDevices;
    }

    @Override
    public DeviceDTO updateDevice(Long oldDeviceId, DeviceDTO newDevice) {
        Optional<Device> oldDevice = deviceRepository.findById(oldDeviceId);
        oldDevice.get().setDescription(newDevice.getDescription());
        oldDevice.get().setAddress(newDevice.getAddress());
        oldDevice.get().setMaximumEnergy(newDevice.getMaximumEnergy());
        Device newSavedDevice = deviceRepository.save(oldDevice.get());
        return deviceMapper.convertToDTO(newSavedDevice);
    }
}
