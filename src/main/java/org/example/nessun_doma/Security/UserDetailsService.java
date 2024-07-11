package org.example.nessun_doma.Security;


import org.example.nessun_doma.Exceptions.UtenteNotFoundException;
import org.example.nessun_doma.Models.SecurityModels.CustomUserDetails;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.example.nessun_doma.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) {

        Utente user = null;
        user = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNotFoundException());

        return new CustomUserDetails(user);
    }



}
