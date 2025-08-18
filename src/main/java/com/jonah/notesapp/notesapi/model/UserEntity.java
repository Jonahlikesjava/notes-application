package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class UserEntity extends BaseEntity {

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String password;

    @Getter
    private String name;

    // in UserEntity
    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
            private List<RoleEntity> roles = new ArrayList<>();

            private boolean enabled;


    // Getters and Setters
    @Setter
    @Getter
    @OneToMany(mappedBy = "user") // A user can have many notes
    private List<NoteEntity> notes;

    @OneToMany(mappedBy = "user")
    private List<FolderEntity> folders;


    // default constructor
    public UserEntity() {}

    public UserEntity(String name, String password, List<RoleEntity> roles, String email) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
}
