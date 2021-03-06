package com.tamimtechnology.projectmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BacklogNotFoundException extends RuntimeException{
    public BacklogNotFoundException(String message) {
        super(message);
    }
}
