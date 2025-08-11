package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import model.base.BaseEntity;

import java.util.List;

@Entity
public class NoteEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Many-to-Many relationship between NoteEntity and TagEntity
// This will create a join table in the database called "note_tags"
// - joinColumns: defines the foreign key to NoteEntity (note_id)
// - inverseJoinColumns: defines the foreign key to TagEntity (tag_id)
// Each note can have many tags, and each tag can belong to many notes

    @ManyToMany
    @JoinTable(
            name = "note_tags",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;
    private String title;
    private String content;

    // Default constructor
    public NoteEntity() {}

    // Parameterized constructor
    public NoteEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and Setters
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

}
