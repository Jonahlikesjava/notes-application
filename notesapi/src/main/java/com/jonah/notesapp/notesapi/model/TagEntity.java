package com.jonah.notesapp.notesapi.model;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import model.base.BaseEntity;

import java.util.List;

@Entity
public class TagEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<NoteEntity> notes;

    public TagEntity() {}

    public TagEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
}
