package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.CorsoService;
import org.example.nessun_doma.Services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public Prenotazione createPrenotazione(@RequestBody Prenotazione prenotazione,  @RequestHeader("Authorization") String authToken) {
        return prenotazioneService.upsertPrenotazione(prenotazione, extractEmailFromToken(authToken));
    }

    @PutMapping
    public Prenotazione updatePrenotazione(@RequestBody Prenotazione prenotazione,  @RequestHeader("Authorization") String authToken) {
        return prenotazioneService.upsertPrenotazione(prenotazione, extractEmailFromToken(authToken));
    }

    @PatchMapping
    public Prenotazione patchPrenotazione(@RequestBody Prenotazione prenotazione,  @RequestHeader("Authorization") String authToken) {
        return prenotazioneService.upsertPrenotazione(prenotazione, extractEmailFromToken(authToken));
    }

    @GetMapping
    public List<Prenotazione> getAllCorsi() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/utente")
    public List<Prenotazione> getAllUtentePrenotazioni(@RequestHeader("Authorization") String authToken) {
        return prenotazioneService.getAllUtentePrenotazioni(extractEmailFromToken(authToken));
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Integer id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id, @RequestHeader("Authorization") String authToken) {
        prenotazioneService.deletePrenotazione(id, extractEmailFromToken(authToken));
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
