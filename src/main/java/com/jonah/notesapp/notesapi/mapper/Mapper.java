package com.jonah.notesapp.notesapi.mapper;

import com.jonah.notesapp.notesapi.dto.UserCreationDTO;
import com.jonah.notesapp.notesapi.dto.UserDTO;
import com.jonah.notesapp.notesapi.model.RoleEntity;
import com.jonah.notesapp.notesapi.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Component
public class Mapper {
    public UserDTO toDto(UserEntity user) {
        String name = user.getUsername();
        List<String> roles = user
                .getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(toList());

        UserDTO dto = new UserDTO(name, roles);
        dto.setUsername((user.getUsername()));
        return dto;
    }

    public UserEntity toUser(UserCreationDTO userDTO) {
        return new UserEntity(
                userDTO.getUsername(),
                userDTO.getPassword(),
                new ArrayList<>(),
                userDTO.getEmail()
        );
    }
}
