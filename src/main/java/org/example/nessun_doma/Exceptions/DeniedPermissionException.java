package org.example.nessun_doma.Exceptions;

public class DeniedPermissionException extends RuntimeException {

    public DeniedPermissionException(String message) {
        super(message);
    }

    public DeniedPermissionException() {}
}
