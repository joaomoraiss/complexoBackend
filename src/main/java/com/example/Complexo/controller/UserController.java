package com.example.Complexo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.Complexo.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.Complexo.model.User;
import com.example.Complexo.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            if (userService.emailExists(user.getStudioEmail())) {
                // Se o email já existe vai retornar um BAD_REQUEST com uma mensagem de erro
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            User newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/searchByName")
    public ResponseEntity<?> getStudioByName(@RequestParam String name){
        try {
            List<User> artistas = userService.getAllUsers();
            List<User> artistasAchados = new ArrayList<>();
            for(User artist : artistas){
                if (artist.getStudioName().trim().equalsIgnoreCase(name.trim())) {
                    artistasAchados.add(artist);
                }
            }
            return ResponseEntity.ok(artistasAchados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @GetMapping("/{studioId}")
    public ResponseEntity<User> getUserById(@PathVariable Long studioId) {
        User user = userService.getUserById(studioId);
        return ResponseEntity.ok(user);
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
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        UserDetails userDetails = userService.findBystudioEmail(email);

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); //
        }

        User user = (User) userDetails;
        return ResponseEntity.ok(user);
    }
}
