package com.fibre.optique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BoiteClientNotFoundException extends RuntimeException {
    public BoiteClientNotFoundException(String message) {
        super(message);
    }
}
