package com.utcn.assignment1.service;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.repository.DeviceRepository;
import com.utcn.assignment1.repository.UserRepository;
import com.utcn.assignment1.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DeviceRepository deviceRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        for(Device device: user.getDevices())
            device.setUser(null);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User oldUser, User newUser) {
        return null;
    }

    @Override
    public User logIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void assignDevicesToUser(Long id, List<Device> devices) {
        Optional<User> user = userRepository.findById(id);
//        System.out.println(user);
        System.out.println(devices);
        if(user.isPresent()) {
            devices.forEach(device -> {
                device.setUser(user.get());
                user.get().addDevice(device);
                userRepository.save(user.get());
                deviceRepository.save(device);
            });
        }
        //user.ifPresent(value -> devices.forEach(value::addDevice));
    }


}
