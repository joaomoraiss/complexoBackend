package com.example.Complexo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Complexo.service.*;
import com.example.Complexo.model.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/{studioId}")
    public ResponseEntity<User> getUserById(@PathVariable Long studioId) {
        Optional<User> user = userService.getUserById(studioId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{studioId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long studioId) {
        userService.deleteUserById(studioId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studioId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long studioId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUserById(studioId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
}
