package org.example.nessun_doma.Controllers;

import jakarta.validation.Valid;
import org.example.nessun_doma.Models.LoginResponse;
import org.example.nessun_doma.Models.SignupRequest;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody SignupRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = JwtHelper.generateToken(request.getEmail());
        return ResponseEntity.ok(new LoginResponse(request.getEmail(), token));
    }
}
