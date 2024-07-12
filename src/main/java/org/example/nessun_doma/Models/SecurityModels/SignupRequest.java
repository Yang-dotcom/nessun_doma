package org.example.nessun_doma.Models.SecurityModels;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.nessun_doma.Models.Enums.Ruolo;

@Data
public class SignupRequest {

    @NotBlank(message = "Name cannot be blank")
    String nome;
    @NotBlank(message = "surname cannot be blank")
    String cognome;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password;
}
