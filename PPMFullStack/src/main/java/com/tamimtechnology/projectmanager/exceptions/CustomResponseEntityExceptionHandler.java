package com.tamimtechnology.projectmanager.exceptions;

import com.tamimtechnology.projectmanager.model.ProjectTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdentifierException(ProjectIdentifierException exception, WebRequest request){
        ProjectIdentifierExceptionResponse exceptionResponse = new ProjectIdentifierExceptionResponse(exception.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception, WebRequest request){
        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BacklogNotFoundException.class)
    public final ResponseEntity<Object> handleBacklogNotFoundException(BacklogNotFoundException exception, WebRequest request){
        BacklogNotFoundExceptionResponse exceptionResponse = new BacklogNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectTaskNotFoundException.class)
    public final ResponseEntity<Object> handleProjectTaskNotFoundException(ProjectTaskNotFoundException exception, WebRequest request){
        ProjectTaskNotFoundExceptionResponse exceptionResponse = new ProjectTaskNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
