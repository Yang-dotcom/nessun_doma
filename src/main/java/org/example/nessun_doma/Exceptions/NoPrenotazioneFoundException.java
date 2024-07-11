package org.example.nessun_doma.Exceptions;

public class NoPrenotazioneFoundException extends RuntimeException{

    public class CorsoNotFoundException extends RuntimeException {

        public CorsoNotFoundException(int id){
            super("prenotazione con id "+ id + " non trovato");
        }

        public CorsoNotFoundException(){
        }
    }

}
