package org.example.nessun_doma.Controllers;

import jakarta.validation.Valid;
import org.example.nessun_doma.Models.SecurityModels.LoginRequest;
import org.example.nessun_doma.Models.SecurityModels.LoginResponse;
import org.example.nessun_doma.Models.SecurityModels.SignupRequest;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UtenteService utenteService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest requestDto) {
        utenteService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = JwtHelper.generateToken(request.getEmail());
        int userId = utenteService.findUserByEmail(request.getEmail()).getId();
        return ResponseEntity.ok(new LoginResponse(userId, request.getEmail(), token));
    }


}
