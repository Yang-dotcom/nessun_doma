package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Prenotazione;
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
    public Prenotazione createPrenotazione(@RequestBody Prenotazione prenotazione) {
        return prenotazioneService.createPrenotazione(prenotazione);
    }

    @GetMapping
    public List<Prenotazione> getAllCorsi() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Integer id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id) {
        prenotazioneService.deletePrenotazione(id);
    }
}
