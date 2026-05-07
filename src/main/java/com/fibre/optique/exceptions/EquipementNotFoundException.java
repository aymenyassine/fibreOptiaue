package com.fibre.optique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EquipementNotFoundException extends RuntimeException {
    public EquipementNotFoundException(String message) {
        super(message);
    }
}
