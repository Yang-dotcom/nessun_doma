package org.example.nessun_doma.Models.SecurityModels;


import lombok.Data;

@Data
public class LoginResponse {

    private int id;
    String email;
    String token;
    String ruolo;

    public LoginResponse(int id, String email, String token, String ruolo) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.ruolo = ruolo;
    }
}
