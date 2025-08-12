package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.TagEntity;
import com.jonah.notesapp.notesapi.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Get all tags
    public List<TagEntity> getAllTags() {
        return tagRepository.findAll();
    }

    // Save a tag
    public TagEntity saveTag(TagEntity tag) {
        return tagRepository.save(tag);
    }

    // Get a tag by ID
    public Optional<TagEntity> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    // Update tag
    public TagEntity updateTag(TagEntity tag) {
        return tagRepository.save(tag);
    }

    // Delete tag by ID
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }
}
