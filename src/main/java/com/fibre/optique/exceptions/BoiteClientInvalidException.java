package com.fibre.optique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BoiteClientInvalidException extends RuntimeException {
    public BoiteClientInvalidException(String message) {
        super(message);
    }
}
