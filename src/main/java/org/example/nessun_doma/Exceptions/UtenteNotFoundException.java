package org.example.nessun_doma.Exceptions;

public class UtenteNotFoundException extends RuntimeException{

    public UtenteNotFoundException(int id){
        super("Utente con id " + id + " not trovato");
    }
}
