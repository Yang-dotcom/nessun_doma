package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
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
    public Corso createCorso(@RequestBody Corso corso, @RequestParam int utenteid) {
        return corsoService.upSertCorso(corso, utenteid);
    }

    @GetMapping
    public List<Corso> getAllCorsi() {
        return corsoService.getAllCorsi();
    }

    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable Integer id) {
        return corsoService.getCorsoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id, @RequestParam int utenteid) {
        corsoService.deleteCorso(id, utenteid);
    }

    @GetMapping("/utenti/{corso_id}")
    public List<Utente> getUtentiByCorso(@PathVariable Long corso_id) {
        Corso corso = new Corso();
        corso.setId(Math.toIntExact(corso_id));
        return corsoService.findUtentiByCorso(corso);
    }
}
