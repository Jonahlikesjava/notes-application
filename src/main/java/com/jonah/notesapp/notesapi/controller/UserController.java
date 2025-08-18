package com.jonah.notesapp.notesapi.controller;


import com.jonah.notesapp.notesapi.dto.UserCreationDTO;
import com.jonah.notesapp.notesapi.dto.UserDTO;
import com.jonah.notesapp.notesapi.dto.UserIdDTO;
import com.jonah.notesapp.notesapi.mapper.Mapper;
import com.jonah.notesapp.notesapi.model.UserEntity;
import com.jonah.notesapp.notesapi.service.RoleService;
import com.jonah.notesapp.notesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final Mapper mapper;


    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @PostMapping
    public UserIdDTO create(@RequestBody UserCreationDTO userDTO) {
        UserEntity user = mapper.toUser(userDTO);

        userDTO.getRoles()
                .stream()
                .map(role -> roleService.getOrCreate(role))
                .forEach(user::addRole);

                userService.save(user);

                return new UserIdDTO(user.getId());
    }
}
