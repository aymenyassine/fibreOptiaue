package com.fibre.optique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CheminFibreNotFoundException extends RuntimeException {
    public CheminFibreNotFoundException(String message) {
        super(message);
    }
}
