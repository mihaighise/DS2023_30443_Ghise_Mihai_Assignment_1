package com.utcn.assignment1.controller;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.UserDTO;
import com.utcn.assignment1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addUser(user));
    }

    @DeleteMapping(value = "/delete/{username}")
    public void deleteCinema(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseEntity<UserDTO> logIn(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.ok().body(userService.logIn(username, password));
    }

    @PutMapping(value = "/assignDevices/{id}")
    public void assignDevicesToUser(@PathVariable Long id, @RequestBody List<Device> devices) {
        userService.assignDevicesToUser(id, devices);
    }

    @PutMapping(value = "/update/{oldUsername}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String oldUsername, @RequestBody User newUser) {
        return ResponseEntity.ok().body(userService.updateUser(oldUsername, newUser));
    }
}
