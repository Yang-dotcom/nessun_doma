package org.example.nessun_doma.Services;


import org.example.nessun_doma.Models.CustomUserDetails;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        Utente user = null;
        try {
            user = utenteRepository.findByEmail(email).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return new CustomUserDetails(user);
    }

}
