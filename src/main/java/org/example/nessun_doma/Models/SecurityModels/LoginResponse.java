package org.example.nessun_doma.Models.SecurityModels;


import lombok.Data;

@Data
public class LoginResponse {

    private int id;
    String email;
    String token;

    public LoginResponse(int id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
}
