package com.jonah.notesapp.notesapi.repository;

import com.jonah.notesapp.notesapi.model.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
}
