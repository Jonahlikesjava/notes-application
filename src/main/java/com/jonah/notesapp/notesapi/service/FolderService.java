package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.FolderEntity;
import com.jonah.notesapp.notesapi.repository.FolderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private static final Logger logger = LoggerFactory.getLogger(FolderService.class);

    // Constructor
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    // Get all folders
    public List<FolderEntity> getAllFolders() {
        return folderRepository.findAll();
    }

    // Save a folder to the database
    public ResponseEntity<String> saveFolder(FolderEntity folder) {
        if (folder != null) {
            folderRepository.save(folder);
            return ResponseEntity.status(HttpStatus.CREATED).body("Folder saved successfully.");
        } else {
            logger.info("Folder doesn't exist. Cannot save.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Folder doesn't exist. Cannot save.");
        }
    }

    // Check if the folder title is empty
    // If the folder title put a default title in place
    public FolderEntity emptyFolderTitle(FolderEntity folderEntity) {
        if (folderEntity.getTitle() == null || folderEntity.getTitle().trim().isEmpty()) {
            folderEntity.setTitle("Untitled Folder");
        }
        return folderEntity;
    }

    // Get a folder by ID
    public Optional<FolderEntity> getFolderByID(Long id) {
        return folderRepository.findById(id);
    }

    // Update a folder
    public ResponseEntity<String> updateFolder(FolderEntity folder) {
        folder = emptyFolderTitle(folder);
       folderRepository.save(folder);
        return ResponseEntity.status(HttpStatus.OK).body("Folder updated successfully.");
    }

    // Delete a folder by ID
    public void deleteFolderById(Long id) {
        folderRepository.deleteById(id);
    }
}
