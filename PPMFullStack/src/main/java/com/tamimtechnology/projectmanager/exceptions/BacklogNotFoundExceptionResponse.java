package com.tamimtechnology.projectmanager.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacklogNotFoundExceptionResponse {
    private String BacklogNotFound;

    public BacklogNotFoundExceptionResponse(String backlogNotFound) {
        BacklogNotFound = backlogNotFound;
    }
}
