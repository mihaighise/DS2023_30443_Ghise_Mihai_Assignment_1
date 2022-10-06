package com.utcn.assignment1.controller;

import com.utcn.assignment1.model.User;
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
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addUser(user));
    }

    @DeleteMapping(value = "/delete/{username}")
    public void deleteCinema(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseEntity<User> logIn(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.ok().body(userService.logIn(username, password));
    }
}
