package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String name;

    private String displayUsername; // stores exactly what the user typed
    private String displayEmail;    // stores exactly what the user typed

    @ManyToMany(fetch = FetchType.EAGER)
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

    public UserEntity(String name, String password, List<RoleEntity> roles, String email) {
        this.username = name;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
}
