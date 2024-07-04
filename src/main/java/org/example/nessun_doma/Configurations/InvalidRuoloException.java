package org.example.nessun_doma.Configurations;

public class InvalidRuoloException extends RuntimeException{


    public InvalidRuoloException(String message){
        super(message);
    }

    public InvalidRuoloException(){
        super("Invalid Ruolo");
    }
}
