package com.jonah.notesapp.notesapi.dto;

import java.util.List;

import static java.util.Arrays.stream;

public class UserCreationDTO {

    private String email;
private String name;
private String password;
private List<String> roles;

    public UserCreationDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }




}
