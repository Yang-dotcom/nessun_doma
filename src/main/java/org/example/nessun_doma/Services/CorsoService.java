package org.example.nessun_doma.Services;

import org.example.nessun_doma.Exceptions.CorsoNotFoundException;
import org.example.nessun_doma.Exceptions.DeniedPermissionException;
import org.example.nessun_doma.Exceptions.InvalidRuoloException;
import org.example.nessun_doma.Exceptions.UtenteNotFoundException;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Enums.Ruolo;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.example.nessun_doma.Repositories.CorsoRepository;

import java.util.List;

@Service
@ComponentScan(basePackageClasses= CorsoRepository.class)
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private UtenteRepository utenteRepository;


    public Corso upsertCorso(Corso corso, String email) throws UtenteNotFoundException, InvalidRuoloException {
        Utente istruttore = utenteRepository.findById(corso.getId())
                            .orElseThrow(() -> new UtenteNotFoundException());

        if(!isSameUser(istruttore, email)){
            throw new DeniedPermissionException();
        }
        return corsoRepository.save(corso);
    }

    public List<Corso> getAllCorsi() {
        return corsoRepository.findAll();
    }


    public Corso getCorsoById(int id) {
        return corsoRepository.findById(id).orElseThrow(
                () -> new CorsoNotFoundException(id));
    }

    public void deleteCorso(int idCorso, String email) {
        Corso temp = corsoRepository.findById(idCorso).orElseThrow(() -> new CorsoNotFoundException(idCorso));
        Utente istruttore = utenteRepository.findById(temp.getIstruttore_id())
                .orElseThrow(() -> new UtenteNotFoundException());

        if(!isSameUser(istruttore, email)){
            throw new DeniedPermissionException();
        }
        corsoRepository.deleteById(idCorso);
    }

    public List<Utente> findUtentiByCorso(Corso corso) {
        return corsoRepository.findDistinctUtenteByCorso(corso);
    }

    private boolean isSameUser(Utente utente, String email){
        if(utente.getEmail().equals(email)){
            return true;
        }
        return false;
    }
}
