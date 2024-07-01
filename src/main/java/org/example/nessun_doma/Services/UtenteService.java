package org.example.nessun_doma.Services;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Services.Repositories.PrenotazioneRepository;
import org.example.nessun_doma.Services.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackageClasses= UtenteRepository.class)
@Slf4j
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public List<Utente> getAllUtenti() {
        List<Utente> temp = utenteRepository.findAll();
        temp.stream().forEach(x -> log.info(String.valueOf(x.getId())));
        return temp;
    }

    public Utente getUtenteById(int id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        log.info(String.valueOf(utente.get().getId()));
        return utenteRepository.findById(id).orElse(null);
    }


//    /**
//     * On Delete Cascade costraint is handled by the service layer.
//     * I found this not optimal as it would take a lot of work
//     * to make sure that all tables related to this one are deleted whenever
//     * a foreign key references it
//     * @param id
//     */
//    @Transactional
//    public void deleteUtente(int id) {
//        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtenteId(id);
//        prenotazioneRepository.deleteAll(prenotazioni);
//        utenteRepository.deleteById(id);
//    }

    public Utente createUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void deleteUtente(int id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        utente.ifPresent(utenteRepository::delete);
    }
}
