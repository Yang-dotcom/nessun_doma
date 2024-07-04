package org.example.nessun_doma.Configurations;

public class UtenteNotFoundException extends RuntimeException{

    public UtenteNotFoundException(int id){
        super("Utente con id " + id + " not trovato");
    }
}
