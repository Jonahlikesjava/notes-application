package com.jonah.notesapp.notesapi.mapper;

import com.jonah.notesapp.notesapi.dto.UserCreationDTO;
import com.jonah.notesapp.notesapi.dto.UserDTO;
import com.jonah.notesapp.notesapi.model.RoleEntity;
import com.jonah.notesapp.notesapi.model.UserEntity;
import org.apache.catalina.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Component
public class Mapper {
    public UserDTO toDto(UserEntity user) {
        String name = user.getName();
        List<String> roles = user
                .getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(toList());
        return new UserDTO(name, roles);
    }

    public UserEntity toUser(UserCreationDTO userDTO) {
       return new UserEntity(userDTO.getName(), userDTO.getPassword(), new ArrayList<>());
    }
}
