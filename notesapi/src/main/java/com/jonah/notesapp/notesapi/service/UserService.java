package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Save a user to the database
    public ResponseEntity<String> saveUser(UserEntity user) {
        if (user != null) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully.");
        } else {
            logger.info("User doesn't exist. Cannot save."); // This logs the failed save attempt to the server console for developers to see.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Sends a 400 error back to frontend
                    .body("User doesn't exist. Cannot save."); // This sends an error message back to the frontend so the user knows the save failed.
        }
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
