package com.jonah.notesapp.notesapi.repository;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import com.jonah.notesapp.notesapi.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

}
