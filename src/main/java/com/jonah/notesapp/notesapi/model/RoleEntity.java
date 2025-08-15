package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // Name of the role, i.e, "ADMIN", "USER"

public RoleEntity() {} // No args constructor required by JPA to create entity objects

    public Long getId() {
    return id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

}
