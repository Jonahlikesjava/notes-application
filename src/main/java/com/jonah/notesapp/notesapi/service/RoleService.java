package com.jonah.notesapp.notesapi.service;

import com.jonah.notesapp.notesapi.model.RoleEntity;
import com.jonah.notesapp.notesapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }


    public RoleEntity getOrCreate(String role) {
        Optional<RoleEntity> existingRole = roleRepository.findByName(role);

        if (existingRole.isPresent()) {
            return existingRole.get();
        }

        // If role is not found then create a new one
        RoleEntity newRole = new RoleEntity();
        newRole.setName(role);

        return roleRepository.save(newRole);
    }
}
