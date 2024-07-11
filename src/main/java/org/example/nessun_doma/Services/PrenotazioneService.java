package org.example.nessun_doma.Services;


import org.example.nessun_doma.Exceptions.DeniedPermissionException;
import org.example.nessun_doma.Exceptions.InvalidRuoloException;
import org.example.nessun_doma.Exceptions.NoPrenotazioneFoundException;
import org.example.nessun_doma.Exceptions.UtenteNotFoundException;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Enums.Ruolo;
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

    public Prenotazione upsertPrenotazione(Prenotazione prenotazione,String email)throws  DeniedPermissionException {
        Utente utente = utenteRepository.findById(prenotazione.getId())
                .orElseThrow(() -> new UtenteNotFoundException());

        if(!isSameUser(utente, email)){
            throw new DeniedPermissionException();
        }
        return prenotazioneRepository.save(prenotazione);
    }


    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(int id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public void deletePrenotazione(int id, String email) {

        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NoPrenotazioneFoundException());
        if(!isSameUser(prenotazione.getUtente(), email)){
            throw new DeniedPermissionException();
        }
        prenotazioneRepository.deleteById(id);
    }


    private boolean isSameUser(Utente utente, String email){
        if(utente.getEmail().equals(email)){
            return true;
        }
        return false;
    }
}

