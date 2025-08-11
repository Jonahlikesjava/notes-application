package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteByID(id);
        return "User deleted successfully!";
    }
}
