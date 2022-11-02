package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getUsers();
    UserDTO addUser(User user);
    void deleteUser(String username);
    UserDTO updateUser(String oldUsername, User newUser);
    UserDTO logIn(String username, String password);
    void assignDevicesToUser(Long id, List<Device> devices);
}
