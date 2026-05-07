package com.fibre.optique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SplitterInvalidException extends RuntimeException {
    public SplitterInvalidException(String message) {
        super(message);
    }
}
