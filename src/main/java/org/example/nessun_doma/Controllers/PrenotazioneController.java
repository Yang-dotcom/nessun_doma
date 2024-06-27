package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Services.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private CorsoService corsoService;

    @PostMapping
    public Corso createCorso(@RequestBody Corso corso) {
        return corsoService.createCorso(corso);
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
    public void deleteCorso(@PathVariable Integer id) {
        corsoService.deleteCorso(id);
    }
}
