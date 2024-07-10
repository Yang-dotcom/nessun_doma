package org.example.nessun_doma.Exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message){
        super(message);
    }
}
