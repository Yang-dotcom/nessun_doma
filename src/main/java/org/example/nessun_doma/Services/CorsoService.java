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


    public Corso upSertCorso(Corso corso, int utenteId) throws UtenteNotFoundException, InvalidRuoloException {
        Utente istruttore = utenteRepository.findById(utenteId)
                            .orElseThrow(() -> new UtenteNotFoundException(utenteId));

        if(!isSameUser(istruttore, utenteId)){
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

    public void deleteCorso(int idCorso, int utenteId) {
        Utente istruttore = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException(utenteId));

        if(!isSameUser(istruttore, utenteId)){
            throw new DeniedPermissionException();
        }
        corsoRepository.deleteById(idCorso);
    }

    public List<Utente> findUtentiByCorso(Corso corso) {
        return corsoRepository.findDistinctUtenteByCorso(corso);
    }

    private boolean isSameUser(Utente utente, int utenteid){
        if(utente.getId() == utenteid){
            return true;
        }
        return false;
    }

}
