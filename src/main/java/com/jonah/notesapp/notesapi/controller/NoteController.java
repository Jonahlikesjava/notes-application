package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.NoteEntity;
import com.jonah.notesapp.notesapi.service.NoteService;
import com.jonah.notesapp.notesapi.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NoteController {

    private final NoteService noteService;
    private final TagService tagService;

    public NoteController(NoteService noteService, TagService tagService) {
        this.noteService = noteService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<NoteEntity> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteEntity createNote(@RequestBody NoteEntity note) {
        return noteService.saveNote(note);
    }

    @PutMapping
    public NoteEntity updateNote(@RequestBody NoteEntity note) {
        return noteService.saveNote(note);
    }

    @DeleteMapping("/{id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteANoteById(Long id) {
        noteService.deleteNoteById(id);
    }






}
