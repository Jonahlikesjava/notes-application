package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.exception.UserRegistrationException;
import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Log in credentials
    public void registerUser(UserEntity user) {
        List<String> errors = new ArrayList<>();

        if (user == null) {
            throw new UserRegistrationException("User object cannot be null.");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            errors.add("Username cannot be empty.");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errors.add("Email cannot be empty.");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errors.add("Password cannot be empty.");
        }

        // Only do password checks if password is not null or blank
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            String password = user.getPassword();

            if (password.length() < 8) {
                errors.add("Password must be at least 8 characters.");
            }

            boolean hasDigit = false;
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasSpecial = false;
            String specialChars = "!@#$%^&*()_+=-";

            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) hasDigit = true;
                if (Character.isLowerCase(c)) hasLowerCase = true;
                if (Character.isUpperCase(c)) hasUpperCase = true;
                if (specialChars.contains(String.valueOf(c))) hasSpecial = true;
            }

            if (!hasDigit || !hasUpperCase || !hasLowerCase || !hasSpecial) {
                errors.add("Password must contain uppercase, lowercase, digit, and special character.");
            }
        }

        if (!errors.isEmpty()) {
            throw new UserRegistrationException(String.join(" ", errors));
        }

        // Normalize inputs
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setUsername(user.getUsername().trim().toLowerCase());

        // Check for duplicates
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        // Username validation
        if (user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            throw new IllegalArgumentException("Username must contain 3-20 characters.");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Save the user
        userRepository.save(user);
    }

    // Deletes a user
    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

    // Accepts an ID and returns the user if found, or handles if it isn't found
    public Optional<UserEntity> getUserByID(Long id) {
        return userRepository.findById(id);
    }
}
