package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User addUser(User user);
    void deleteUser(String username);
    User updateUser(String oldUsername, User newUser);
    User logIn(String username, String password);
    void assignDevicesToUser(Long id, List<Device> devices);
}
