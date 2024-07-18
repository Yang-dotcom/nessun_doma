package org.example.nessun_doma.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/corsi")

public class CorsoController {

    private CorsoService corsoService;



    @Autowired
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @PostMapping
    public Corso createCorso(@RequestBody Corso corso, @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @PutMapping
    public Corso updatePrenotazione(@RequestBody Corso corso,  @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @PatchMapping
    public Corso patchPrenotazione(@RequestBody Corso corso,  @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @GetMapping
    public List<Corso> getAllCorsi() {
        return corsoService.getAllCorsi();
    }

    @GetMapping("/available")
    public List<Corso> getAvailableCoursi(){
        return corsoService.getHasFreeSpotsCourses();
    }

    @GetMapping("/istruttore")
    public List<Corso> getAllIstruttoreCorsi(@RequestHeader("Authorization") String authToken) {
        return corsoService.getAllIstruttoreCorsi(extractEmailFromToken(authToken));
    }

    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable Integer id) {
        return corsoService.getCorsoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id, @RequestHeader("Authorization") String authToken) {
        corsoService.deleteCorso(id, extractEmailFromToken(authToken));
    }

    @GetMapping("/utenti/{corso_id}")
    public List<Utente> getUtentiByCorso(@PathVariable Long corso_id) {
        Corso corso = new Corso();
        corso.setId(Math.toIntExact(corso_id));
        return corsoService.findUtentiByCorso(corso);
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
