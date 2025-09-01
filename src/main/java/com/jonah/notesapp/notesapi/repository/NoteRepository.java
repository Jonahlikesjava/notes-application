package com.jonah.notesapp.notesapi.repository;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findByTitle(String title);

    List<NoteEntity> findByTags_IdOrUser_Id(Long tagId, Long userId);

}
