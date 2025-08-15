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
    private String email;
    private String password;
    private String name;

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


    public String getName() {
        return name;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
}
