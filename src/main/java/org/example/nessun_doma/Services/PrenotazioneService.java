package org.example.nessun_doma.Services;


import org.example.nessun_doma.Configurations.InvalidRuoloException;
import org.example.nessun_doma.Configurations.UtenteNotFoundException;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Ruolo;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.PrenotazioneRepository;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackageClasses= PrenotazioneRepository.class)
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Prenotazione upsertPrenotazione(Prenotazione prenotazione,int utenteId)throws UtenteNotFoundException, InvalidRuoloException {
        Utente cliente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException(utenteId));

        if(cliente.getRuolo() != Ruolo.CLIENTE || utenteId != prenotazioneRepository.findById(utenteId).get().getUtente().getId()){
            throw new InvalidRuoloException("solo i clienti possono modificare le prenotazioni o il cliente non può modificare la prenotazione di un altro cliente");
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(int id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public void deletePrenotazione(int id, int utenteId) {

        Utente cliente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException(utenteId));

        if(cliente.getRuolo() != Ruolo.CLIENTE  ||
                utenteId != prenotazioneRepository.findById(id).get().getUtente().getId()){
            throw new InvalidRuoloException("solo i clienti possono modificare le prenotazioni o il cliente non può modificare la prenotazione di un altro cliente");
        }
        prenotazioneRepository.deleteById(id);
    }
}

