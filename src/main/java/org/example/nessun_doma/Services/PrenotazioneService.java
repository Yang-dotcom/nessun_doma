package org.example.nessun_doma.Services;


import org.example.nessun_doma.Exceptions.*;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Enums.Ruolo;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.CorsoRepository;
import org.example.nessun_doma.Repositories.PrenotazioneRepository;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackageClasses= PrenotazioneRepository.class)
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private CorsoRepository corsoRepository;

    public Prenotazione upsertPrenotazione(Prenotazione prenotazione,String email)throws  DeniedPermissionException {
        Utente utente = utenteRepository.findById(prenotazione.getUtente().getId())
                .orElseThrow(() -> new UtenteNotFoundException());

        if(!isSameUser(utente, email)){
            throw new DeniedPermissionException();
        }
        Corso corso = corsoRepository.findById(prenotazione.getCorso().getId()).orElseThrow(() -> new CorsoNotFoundException());
        corso.setAvailableSpots(corso.getAvailableSpots() - 1);
        if(corso.getAvailableSpots() < 0){
            throw new CorsoIsFullException();
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAllUtentePrenotazioni(String email){

        Utente utente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNotFoundException());
        if(!isSameUser(utente, email)){throw new DeniedPermissionException();}

        List<Prenotazione> prenotazioni = prenotazioneRepository.findPrenotazioniByUtente(utente);
        //select only date relevant bookings and delete the rest from the database

        for(Prenotazione p : prenotazioni){
            if (p.getDataPrenotazione().isBefore(LocalDateTime.now())){
                prenotazioneRepository.deleteById(p.getId());
            }
        }

        return prenotazioneRepository.findPrenotazioniByUtente(utente);

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

