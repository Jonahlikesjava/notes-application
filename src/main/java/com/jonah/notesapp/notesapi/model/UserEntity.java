package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import model.base.BaseEntity;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class UserEntity extends BaseEntity {

    private String username;
    public String getId;
    private String email;
    private String password;
    private String name;
    private String id;

    // in UserEntity
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
            private List<RoleEntity> roles = new ArrayList<>();

            private boolean enabled;


    @OneToMany(mappedBy = "user") // A user can have many notes
    private List<NoteEntity> notes;

    @OneToMany(mappedBy = "user")
    private List<FolderEntity> folders;


    // default constructor
    public UserEntity() {}

    public UserEntity(String name, String password, List<RoleEntity> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
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

    public String getName() {
        return name;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
}
