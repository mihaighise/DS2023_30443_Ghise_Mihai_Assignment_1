package com.utcn.assignment1.service;

import com.utcn.assignment1.model.User;
import com.utcn.assignment1.repository.UserRepository;
import com.utcn.assignment1.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

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


}
