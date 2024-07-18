package org.example.nessun_doma.Exceptions;

public class CorsoNotFoundException extends RuntimeException {

    public CorsoNotFoundException(int id){
        super("Corso con id "+ id + " non trovato");
    }

    public CorsoNotFoundException() {

    }
}
