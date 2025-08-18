package com.jonah.notesapp.notesapi.dto;

import java.util.List;

import jakarta.validation.constraints.*;


public class UserCreationDTO {

    @Email(message = "Email must be valid , regexp = \"^[A-Za-z0-9+_.-]+@uni-sofia.com")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message= "Name cannot be blank")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters long")
    private String password;


    @NotNull(message = "Roles are required")
    @Size(min = 1, message = "At least one role must be provided")
    private List<
            @NotBlank(message = "Role cannot be blank")
            @Pattern(regexp = "USER|ADMIN", message = "Allowed roles: USER, ADMIN")
                    String
            > roles;


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
