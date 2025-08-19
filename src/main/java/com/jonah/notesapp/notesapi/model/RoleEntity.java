package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import model.base.BaseEntity;

@Entity
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @jakarta.validation.constraints.NotBlank
    @jakarta.persistence.Column(nullable = false, unique = true, length = 32)
    private String name; // Name of the role, i.e, "ADMIN", "USER"


    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }


}
