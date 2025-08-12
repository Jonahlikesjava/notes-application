package com.jonah.notesapp.notesapi.controller;

import com.jonah.notesapp.notesapi.model.FolderEntity;
import com.jonah.notesapp.notesapi.service.FolderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    // GET /api/folders
    @GetMapping
    public List<FolderEntity> getAllFolders() {
        return folderService.getAllFolders();
    }

    // POST /api/folders
    @PostMapping
    public FolderEntity addFolder(@RequestBody FolderEntity folder) {
        return folderService.saveFolder(folder);
    }

    // PUT /api/folders/{id}
    @PutMapping("/{id}")
    public FolderEntity updateFolder(@RequestBody FolderEntity folder) {
        return folderService.updateFolder(folder);
    }

    // DELETE /api/folders/{id}
    @DeleteMapping("/{id}")
    public String deleteFolder(@PathVariable Long id) {
        folderService.deleteFolderById(id);
        return "Folder deleted successfully!";
    }
}

