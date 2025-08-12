package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.TagEntity;
import com.jonah.notesapp.notesapi.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) { // Constructor injection
        this.tagService = tagService;
    }

    // GET /api/tags
    @GetMapping
    public List<TagEntity> getAllTags() {
        return tagService.getAllTags();
    }

    // POST /api/tags
    @PostMapping
    public TagEntity addTag(@RequestBody TagEntity tag) {
        return tagService.saveTag(tag);
    }

    // PUT /api/tags/{id}
    @PutMapping("/{id}")
    public TagEntity updateTag(@RequestBody TagEntity tag) {
        return tagService.updateTag(tag);
    }

    // DELETE /api/notes/{id}
    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTagById(id);
        return "Tag deleted successfully!";
    }
}

