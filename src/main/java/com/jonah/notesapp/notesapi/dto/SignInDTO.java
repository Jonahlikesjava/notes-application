package com.jonah.notesapp.notesapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {

    @NotBlank(message = "Login (username or email) is required")
    private String login;

    @NotBlank(message = "Password is required")
    private String password;
}
