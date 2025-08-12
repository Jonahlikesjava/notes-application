package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import com.jonah.notesapp.notesapi.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Get all notes
    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    // Save a note to the database
    public NoteEntity saveNote(NoteEntity note) {
        return noteRepository.save(note);
    }

    // Get a note by ID
    public Optional<NoteEntity> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Update a note
    public NoteEntity updateNote(NoteEntity note) {
        return noteRepository.save(note);
    }

    // Delete a note by id
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
