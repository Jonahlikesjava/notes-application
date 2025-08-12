package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import com.jonah.notesapp.notesapi.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) { // Constructor injection
        this.noteService = noteService;
    }

    // GET /api/notes
    @GetMapping
    public List<NoteEntity> getAllNotes() {
        return noteService.getAllNotes();
    }

    // GET /api/notes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<NoteEntity> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/notes
    @PostMapping
    public NoteEntity addNote(@RequestBody NoteEntity note) {
        return noteService.saveNote(note);
    }

    // PUT /api/notes/{id}
    @PutMapping("/{id}")
    public NoteEntity updateNote(@RequestBody NoteEntity note) {
        return noteService.updateNote(note);
    }

    // DELETE /api/notes/{id}
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return "Note deleted successfully!";
    }
}
