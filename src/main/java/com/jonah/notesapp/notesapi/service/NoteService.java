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

    // Delete a note by id
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    // Find notes by title
    public  List<NoteEntity> findNotesByTitle(String title) {
       return noteRepository.findByTitle(title);
    }

    // Find notes by tag or user id
    public List<NoteEntity> findByTags_IdOrUser_Id(Long tagId, Long userId) {
        return noteRepository.findByTags_IdOrUser_Id(tagId, userId);
    }



}
