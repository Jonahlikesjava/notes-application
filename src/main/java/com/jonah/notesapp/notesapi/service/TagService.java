package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.TagEntity;
import com.jonah.notesapp.notesapi.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    // Constructor
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Get all tags
    public List<TagEntity> getAllTags() {
        return tagRepository.findAll();
    }

    // Save a tag
    public ResponseEntity<String> saveTag(TagEntity tag) {
        if (tag != null) {
            tagRepository.save(tag);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tag saved successfully.");
        } else {
            logger.info("Tag doesn't exist. Cannot save.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tag doesn't exist. Cannot save.");
        }
    }

    // Get a tag by ID
    public Optional<TagEntity> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    // Update tag
    public ResponseEntity<String> updateTag(TagEntity tag) {
        tagRepository.save(tag);
        return ResponseEntity.status(HttpStatus.OK).body("Tag updated successfully.");
    }

    // Delete tag by ID
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }
}

