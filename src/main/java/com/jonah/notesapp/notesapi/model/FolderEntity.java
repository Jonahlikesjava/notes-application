package com.jonah.notesapp.notesapi.model;

import jakarta.persistence.*;
import model.base.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FolderEntity extends BaseEntity {

    // Links this entity to a specific UserEntity (foreign key user_id).
    // Many of these entities can belong to the same user.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public FolderEntity() {}

    public FolderEntity(UserEntity user, String title) {
        this.user = user;
        this.title = title;
    }

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
}
