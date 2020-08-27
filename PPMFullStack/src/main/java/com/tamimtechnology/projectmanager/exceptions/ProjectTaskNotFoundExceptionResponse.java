package com.tamimtechnology.projectmanager.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectTaskNotFoundExceptionResponse {
    private String projectTaskNotFound;

    public ProjectTaskNotFoundExceptionResponse(String projectTaskNotFound) {
        this.projectTaskNotFound = projectTaskNotFound;
    }
}
