package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.dto.SignInDTO;
import com.jonah.notesapp.notesapi.dto.UserCreationDTO;
import com.jonah.notesapp.notesapi.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserCreationDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Invalid registration request.");
        }
        authService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> logInUser(@RequestBody SignInDTO dto, HttpServletRequest request) {
        if (dto == null || dto.getLogin() == null || dto.getPassword() == null) {
            return ResponseEntity.badRequest().body("Login and password are required.");
        }

        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());

            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

            // ensure a session exists so JSESSIONID is issued
            request.getSession(true);

            return ResponseEntity.ok("Log in was successfully done!");
        } catch (BadCredentialsException ex) {
            // wrong username/email or password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/email or password.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws ServletException {
        // invalidates session and clears SecurityContext
        request.logout();
        return ResponseEntity.ok().build();
    }
}
