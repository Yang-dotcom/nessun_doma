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

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackageClasses= CorsoRepository.class)
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private UtenteRepository utenteRepository;


    public Corso upsertCorso(Corso corso, String email) throws UtenteNotFoundException, InvalidRuoloException {

        Utente istruttore = utenteRepository.findByEmail(email)
                            .orElseThrow(() -> new UtenteNotFoundException());

        if(!isSameUser(istruttore, email)){
            throw new DeniedPermissionException();
        }

        corso.setAvailableSpots(corso.getMaxPartecipanti());
        corso.setIstruttore_id(istruttore.getId());
        return corsoRepository.save(corso);
    }

    public Corso upsertCorso(Corso corso){
        return corsoRepository.save(corso);
    }

    public List<Corso> getAllCorsi() {
        return corsoRepository.findAll();
    }


    public List<Corso> getAllIstruttoreCorsi(String email){
        Utente utente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNotFoundException());
        if(!isSameUser(utente, email)){throw new DeniedPermissionException();}

        List<Corso> corsi = corsoRepository.findcorsiByUtente(utente.getId());

        for(Corso corso: corsi){
            if(corso.getDataFine().isBefore(LocalDateTime.now())){
                corsoRepository.deleteById(corso.getId());
            }
        }
        return corsoRepository.findcorsiByUtente(utente.getId());
    }

    public List<Corso> getHasFreeSpotsCourses(){
        List<Corso> corsi = corsoRepository.findAll();

        // TODO: handle null cases, show only courses which are valid date wise
        return corsi.stream().filter(x -> x.getAvailableSpots() != null && x.getAvailableSpots() > 0).toList();

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



    public Corso findCorsoByName(String corsoName) {
        return corsoRepository.findByNome(corsoName).orElseThrow(
                () -> new CorsoNotFoundException(corsoName));

    }
}
