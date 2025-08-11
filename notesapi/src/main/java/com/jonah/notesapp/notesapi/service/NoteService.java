package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import com.jonah.notesapp.notesapi.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    NoteRepository noteRepository;
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    // Constructor
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Get all notes
    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    // Save a note to the database
    public ResponseEntity<String> saveNote(NoteEntity note) {
        if (note != null) {
            noteRepository.save(note);
            return ResponseEntity.status(HttpStatus.CREATED).body("Note saved successfully.");
        } else {
            logger.info("Note doesn't exist. Cannot save.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Note doesn't exist. Cannot save.");
        }
    }

    // Delete a note
    public void deleteByID(Long id) {
        noteRepository.deleteById(id);
    }

    // Get a note by ID
    public Optional<NoteEntity> getNoteByID(Long id) {
        return noteRepository.findById(id);
    }

    // Updates a note
    public ResponseEntity<String> updateNote(NoteEntity note) {
        noteRepository.save(note);
        return ResponseEntity.status(HttpStatus.OK).body("Note updated successfully.");

    }

    // Deletes a note by id
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
