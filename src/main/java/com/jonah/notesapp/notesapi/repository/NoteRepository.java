package com.jonah.notesapp.notesapi.repository;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

}
