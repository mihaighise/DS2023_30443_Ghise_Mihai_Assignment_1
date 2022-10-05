package com.utcn.assignment1.service.interfaces;

import com.utcn.assignment1.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User addUser(User user);
    void deleteUser(String username);
    User updateUser(User oldUser, User newUser);
    User logIn(String username, String password);
}
