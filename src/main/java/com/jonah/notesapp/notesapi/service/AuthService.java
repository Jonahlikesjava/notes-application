package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.dto.SignInDTO;
import com.jonah.notesapp.notesapi.dto.UserCreationDTO;
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

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;
    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern USERNAME = Pattern.compile("^[a-zA-Z0-9._-]{3,20}$");

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(UserCreationDTO dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            throw new UserRegistrationException("Invalid registration request. Please try again.");
        }

        String username = dto.getUsername();
        String email = dto.getEmail();
        String name = dto.getName();
        String rawPassword = dto.getPassword();

        if (username == null || username.isBlank()) {
            errors.add("Username cannot be empty.");
            logger.warn("Username was empty");
        } else {
            username = username.trim();
            if (!isUsername(username)) {
                errors.add("Invalid username format.");
                logger.warn("Username has invalid format for: {}", username);
            }
            username = username.toLowerCase();
        }

        if (email == null || email.isBlank()) {
            errors.add("Email cannot be empty.");
            logger.warn("Email was empty.");
        } else {
            email = email.trim();
            if (!isEmail(email)) {
                errors.add("Invalid email format.");
                logger.warn("Email has invalid format for : {}", email);
            }
            email = email.toLowerCase();
        }

        if (rawPassword == null || rawPassword.isBlank()) {
            errors.add("Password cannot be empty.");
            logger.warn("Password was empty.");
        } else {
            if (rawPassword.length() < 8) {
                errors.add("Password must be at least 8 characters.");
            }
            boolean hasDigit = false;
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasSpecial = false;
            String specialChars = "!@#$%^&*()_+=-";
            for (char c : rawPassword.toCharArray()) {
                if (Character.isDigit(c)) hasDigit = true;
                if (Character.isLowerCase(c)) hasLowerCase = true;
                if (Character.isUpperCase(c)) hasUpperCase = true;
                if (specialChars.indexOf(c) >= 0) hasSpecial = true;
            }
            if (!hasDigit || !hasUpperCase || !hasLowerCase || !hasSpecial) {
                errors.add("Password must contain uppercase, lowercase, digit, and special character.");
            }
        }

        if (username != null && !username.isBlank() && userRepository.existsByUsername(username)) {
            errors.add("Username already exists.");
            logger.warn("Username is a duplicate: {}", username);
        }
        if (email != null && !email.isBlank() && userRepository.existsByEmail(email)) {
            errors.add("Email already exists.");
            logger.warn("Email is a duplicate: {}", email);
        }

        if (!errors.isEmpty()) {
            logger.error("User registration failed due to errors: {}", errors);
            throw new UserRegistrationException(String.join(" ", errors));
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setName(name);
        entity.setDisplayUsername(username);
        entity.setDisplayEmail(email);
        entity.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(entity);
        logger.info("User registered successfully: {}", username);
    }

    public void signIn(SignInDTO dto) {
       if (dto == null || dto.getLogin() == null || dto.getPassword() == null) {
           throw new UserRegistrationException("Invalid sign-in");
       }

       String login = dto.getLogin().trim();
       String rawPassword = dto.getPassword();

       if (login.isBlank() || rawPassword.isBlank()) {
           throw new UserRegistrationException("Login and password are required");
       }

       Optional<UserEntity> userOpt = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(login, login);

       UserEntity user = userOpt.orElseThrow(() ->
               new UserRegistrationException("Invalid username/email or password."));

       if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
           throw new UserRegistrationException("Invalid username/email or password");
       }

        logger.info("User signed in successfully: {}", user.getUsername());

    }

    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

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
