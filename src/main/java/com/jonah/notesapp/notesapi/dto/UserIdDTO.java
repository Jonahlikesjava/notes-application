package com.jonah.notesapp.notesapi.dto;

public class UserIdDTO {
    private final Long id;

    public UserIdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
