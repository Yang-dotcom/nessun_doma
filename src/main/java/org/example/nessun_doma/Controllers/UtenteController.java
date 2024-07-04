package org.example.nessun_doma.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")

public class UtenteController {


    @Autowired
    private UtenteService utenteService;

    @PostMapping
    public Utente createUtente(@RequestBody Utente utente) {
        return utenteService.createUtente(utente);
    }

    @PutMapping
    public Utente updateUtente(@RequestBody Utente utente) {
        return utenteService.createUtente(utente);
    }

    @PatchMapping
    public Utente patchUtente(@RequestBody Utente utente) {
        return utenteService.createUtente(utente);
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
    public void deleteUtente(@PathVariable int id) {
        utenteService.deleteUtente(id);
    }

    @GetMapping("/corsi/{utente_id}")
    public List<Corso> getCorsiByUtente(@PathVariable Long utente_id) {
        Utente utente = new Utente();
        utente.setId(Math.toIntExact(utente_id));
        return utenteService.findCorsiByUtente(utente);
    }
}
