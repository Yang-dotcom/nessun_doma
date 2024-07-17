package org.example.nessun_doma.Services;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.nessun_doma.Exceptions.DeniedPermissionException;
import org.example.nessun_doma.Exceptions.DuplicateException;
import org.example.nessun_doma.Exceptions.UtenteNotFoundException;
import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.SecurityModels.SignupRequest;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackageClasses= UtenteRepository.class)
@Slf4j
@Transactional()
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

    public Utente updateUtente(Utente utente, String email) {
        Utente temp = utenteRepository.findById(utente.getId()).orElseThrow(null);

        if(temp !=  null && !isSameUser(temp, email)) throw new DeniedPermissionException();

        return utenteRepository.save(utente);
    }

    public Utente createUtente(Utente utente){
        return utenteRepository.save(utente);
    }


    public void deleteUtente(int id, String email) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new UtenteNotFoundException(id));

        if(!isSameUser(utente, email)) throw new DeniedPermissionException();

        utenteRepository.deleteById(id);
    }

    public List<Corso> findCorsiByUtente(Utente utente) {
        return utenteRepository.findDistinctCorsoByUtente(utente);
    }

    @Transactional
    public void signup(SignupRequest request) {
        String email = request.getEmail();
        Optional<Utente> existingUser = utenteRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }

        //TODO implement hashed password
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Utente user = Utente.builder().email(request.getEmail()).nome(request.getNome()).cognome(request.getCognome())
                .ruolo(request.getRuolo()).password(hashedPassword).build();
        utenteRepository.save(user);
    }

    public Utente findUserByEmail(String email){
        return utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNotFoundException());
    }

    private boolean isSameUser(Utente utente, String email){
        if(utente.getEmail().equals(email)){
            return true;
        }
        return false;
    }


}
