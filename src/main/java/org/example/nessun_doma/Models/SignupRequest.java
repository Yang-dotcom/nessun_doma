package org.example.nessun_doma.Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Name cannot be blank")
    String name;
    @NotBlank(message = "surname cannot be blank")
    String surName;

    @Enumerated(EnumType.STRING)
    private Ruolo role;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password;
}
