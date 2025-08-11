package com.jonah.notesapp.notesapi.exception;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }
}
