package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.base.BaseEntity;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class NoteEntity extends BaseEntity {

    // Getters and Setters
    // Links this entity to a specific UserEntity (foreign key user_id).
    // Many of these entities can belong to the same user.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @ManyToMany
    @JoinTable(
            name = "note_tags",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;
    private String title;
    private String content;

    // Parameterized constructor
    public NoteEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
