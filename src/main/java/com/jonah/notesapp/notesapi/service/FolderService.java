package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.FolderEntity;
import com.jonah.notesapp.notesapi.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    // Get all folders
    public List<FolderEntity> getAllFolders() {
        return folderRepository.findAll();
    }

    // Save a folder to the database
    public FolderEntity saveFolder(FolderEntity folder) {
        return folderRepository.save(applyDefaultTitle(folder));
    }

    // Get a folder by ID
    public Optional<FolderEntity> getFolderById(Long id) {
        return folderRepository.findById(id);
    }

    // Update a folder
    public FolderEntity updateFolder(FolderEntity folder) {
        return folderRepository.save(applyDefaultTitle(folder));
    }

    // Delete a folder by ID
    public void deleteFolderById(Long id) {
        folderRepository.deleteById(id);
    }

    private FolderEntity applyDefaultTitle(FolderEntity folder) {
        if (folder.getTitle() == null || folder.getTitle().trim().isEmpty()) {
            folder.setTitle("Untitled Folder");
        }
        return folder;
    }
}
