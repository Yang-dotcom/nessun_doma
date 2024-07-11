package org.example.nessun_doma.Exceptions;

import org.example.nessun_doma.Services.UtenteService;

public class UtenteNotFoundException extends RuntimeException{

    public UtenteNotFoundException(int id){
        super("Utente con id " + id + " not trovato");
    }

    public UtenteNotFoundException(){}
}

