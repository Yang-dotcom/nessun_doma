package org.example.nessun_doma.Services;


import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Services.Repositories.PrenotazioneRepository;
import org.example.nessun_doma.Services.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackageClasses= PrenotazioneRepository.class)
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione createPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return (List<Prenotazione>) prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(int id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public void deletePrenotazione(int id) {
        prenotazioneRepository.deleteById(id);
    }







}
