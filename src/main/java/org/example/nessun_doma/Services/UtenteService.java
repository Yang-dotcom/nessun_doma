package org.example.nessun_doma.Services;


import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Services.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackageClasses= UtenteRepository.class)
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;


    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteById(int id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public void deleteUtente(int id) {
        utenteRepository.deleteById(id);
    }

    public Utente createUtente(Utente utente) {
        return utenteRepository.save(utente);
    }
}
