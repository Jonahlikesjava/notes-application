package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }


}

