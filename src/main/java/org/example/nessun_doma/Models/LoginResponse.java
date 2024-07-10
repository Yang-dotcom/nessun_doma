package org.example.nessun_doma.Models;


import lombok.Data;

@Data
public class LoginResponse {

    String email;
    String token;

    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
