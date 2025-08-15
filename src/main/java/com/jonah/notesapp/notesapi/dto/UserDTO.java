package com.jonah.notesapp.notesapi.dto;



import java.util.List;

public class UserDTO {

    private String name;
    private List<String> roles;

    public UserDTO(String name, List<String> roles) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }





}
