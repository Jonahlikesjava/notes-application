package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public UserEntity save(UserEntity user) {
        if (user == null) {
            logger.warn("Attempted to save null user");
            throw new IllegalArgumentException("User cannot be null");
        }
        UserEntity saved = userRepository.save(user);
        logger.info("Saved user id={}, username={}", saved.getId(), saved.getUsername());
        return saved;
    }


    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            logger.warn("Delete requested for non-existent user id={}", id);
            return false;
        }
        userRepository.deleteById(id);
        logger.info("Deleted user id={}", id);
        return true;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }
}
