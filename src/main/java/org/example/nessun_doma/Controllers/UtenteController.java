package org.example.nessun_doma.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
@Slf4j
public class UtenteController {


    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping
    public Utente createUtente(@RequestBody Utente utente) {
        return utenteService.createUtente(utente);
    }

    @PutMapping
    public Utente updateUtente(@RequestBody Utente utente, @RequestHeader("Authorization") String authToken) {
        return utenteService.updateUtente(utente,extractEmailFromToken(authToken));
    }

    @PatchMapping
    public Utente patchUtente(@RequestBody Utente utente, @RequestHeader("Authorization") String authToken) {
        return utenteService.updateUtente(utente, extractEmailFromToken(authToken));
    }

    @GetMapping
    public List<Utente> getAllUtenti() {
        return utenteService.getAllUtenti();
    }

    @GetMapping("/{id}")
    public Utente getUtenteById(@PathVariable int id) {
        return utenteService.getUtenteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUtente(@PathVariable int id, @RequestHeader("Authorization") String authToken) {
        utenteService.deleteUtente(id, extractEmailFromToken(authToken));
    }

    @GetMapping("/corsi/{utente_id}")
    public List<Corso> getCorsiByUtente(@PathVariable Long utente_id) {
        Utente utente = new Utente();
        utente.setId(Math.toIntExact(utente_id));
        return utenteService.findCorsiByUtente(utente);
    }

    private String extractEmailFromToken(String authHeader){
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = JwtHelper.extractUsername(token);
        }
        return username;
    }
}
