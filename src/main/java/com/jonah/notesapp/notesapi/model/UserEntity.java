package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import model.base.BaseEntity;

import java.util.List;
import java.util.Objects;

@Entity
public class UserEntity extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private String role;
    private boolean enabled;

    @OneToMany(mappedBy = "user") // A user can have many notes
    private List<NoteEntity> notes;

    @OneToMany(mappedBy = "user")
    private List<FolderEntity> folders;


    // default constructor
    public UserEntity() {}

    // parameterized constructor
    public UserEntity(String email, String passwordHash, String username, List<NoteEntity> notes) {
        this.email = email;
        this.username = username;
        this.notes = notes;
    }

    // Getters and Setters
    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // check if the username is not blank
    public boolean hasUsername() {
        return username != null;
    }

    // Checks if the user input matches the username that is stored
    public boolean isUsernameMatch(String input) {
        return Objects.equals(username, input);
    }

    // Ensures the password is present before authentication
    public boolean hasPassword() {
        return password != null && !password.isBlank();
    }

    // Checks if the username and password are both present
    public boolean isValidForLogin() {
        return hasUsername() && hasPassword();
    }
}
