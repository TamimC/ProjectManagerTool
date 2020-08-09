package com.tamimtechnology.projectmanager.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectIdentifierExceptionResponse {

    private String projectIdentifier;

    public ProjectIdentifierExceptionResponse (String projectIdentifier){
        this.projectIdentifier = projectIdentifier;
    }
}
