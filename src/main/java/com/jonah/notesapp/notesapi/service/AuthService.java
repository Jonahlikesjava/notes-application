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
import java.util.regex.Pattern;


@Service
public class AuthService {

    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;
    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern USERNAME = Pattern.compile("^[a-zA-Z0-9._-]{3,20}$");

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
            throw new UserRegistrationException("Invalid registration request. Please try again.");
        }

        // Username: trim first, then validate & normalize
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            errors.add("Username cannot be empty.");
            logger.warn("Username was empty");
        } else {
            user.setUsername(user.getUsername().trim()); // Trim first
            if (!isUsername(user.getUsername())) {
                errors.add("Invalid username format.");
                logger.warn("Username has invalid format for: {}", user.getUsername());
            }
            user.setUsername(user.getUsername().toLowerCase()); // Lowercase after validation
        }

        // Email: trim first, then validate & normalize
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errors.add("Email cannot be empty.");
            logger.warn("Email was empty.");
        } else {
            user.setEmail(user.getEmail().trim()); // Trim first
            if (!isEmail(user.getEmail())) {
                errors.add("Invalid email format.");
                logger.warn("Email has invalid format for : {}", user.getEmail());
            }
            user.setEmail(user.getEmail().toLowerCase()); // Lowercase after validation
        }

        // Password checks
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errors.add("Password cannot be empty.");
            logger.warn("Password was empty.");
        } else {
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

        // Duplicate checks only when values are present
        if (user.getUsername() != null && !user.getUsername().isBlank()
                && userRepository.existsByUsername(user.getUsername())) {
            errors.add("Username already exists.");
            logger.warn("Username is a duplicate: {}", user.getUsername());
        }

        if (user.getEmail() != null && !user.getEmail().isBlank()
                && userRepository.existsByEmail(user.getEmail())) {
            errors.add("Email already exists.");
            logger.warn("Email is a duplicate: {}", user.getEmail());
        }

        if (!errors.isEmpty()) {
            logger.error("User registration failed due to errors: {}", errors);
            throw new UserRegistrationException(String.join(" ", errors));
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);


        userRepository.save(user);
        logger.info("User registered successfully: {}", user.getUsername());
    }

    // Deletes a user
    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

    // Accepts an ID and returns the user if found, or handles if it isn't found
    public Optional<UserEntity> getUserByID(Long id) {
        return userRepository.findById(id);
    }

    private static boolean isEmail(String s) {
        return EMAIL.matcher(s).matches();
    }

    private static boolean isUsername(String s) {
        return USERNAME.matcher(s).matches();
    }
}
