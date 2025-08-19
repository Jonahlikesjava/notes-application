package com.jonah.notesapp.notesapi.dto;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserCreationDTO {

    @Email(
            regexp = "^[A-Za-z0-9._%+-]+@example\\.com$",
            message = "Email must be an @example.com address"
    )
    @NotBlank(message = "Email is required")
    private String email;


    @NotBlank(message= "Name cannot be blank")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters long")
    private String password;

    private String username;

    @NotNull(message = "Roles are required")
    @Size(min = 1, message = "At least one role must be provided")
    private List<
            @NotBlank(message = "Role cannot be blank")
            @Pattern(regexp = "USER|ADMIN", message = "Allowed roles: USER, ADMIN")
                    String
            > roles;


    public UserCreationDTO() {
    }

}
