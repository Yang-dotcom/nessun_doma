package org.example.nessun_doma.Exceptions;

import lombok.Data;

@Data
public class ApiErrorResponse {
    int errorCode; String description;

    public ApiErrorResponse(int errorCode, String description){
        this.errorCode = errorCode;
        this.description = description;
    }


}
