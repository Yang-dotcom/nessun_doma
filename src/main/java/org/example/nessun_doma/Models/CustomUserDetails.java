package org.example.nessun_doma.Models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data

public class CustomUserDetails implements UserDetails {

    private final Utente utente;


    public CustomUserDetails(Utente utente) {
        this.utente = utente;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(utente.getRuolo().name()));
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getEmail();
    }
}
