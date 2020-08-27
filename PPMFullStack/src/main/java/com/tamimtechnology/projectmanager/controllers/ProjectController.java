package com.tamimtechnology.projectmanager.controllers;

import com.tamimtechnology.projectmanager.model.Project;
import com.tamimtechnology.projectmanager.services.ErrorValidationService;
import com.tamimtechnology.projectmanager.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final ErrorValidationService errorValidationService;

    public ProjectController(ProjectService projectService, ErrorValidationService errorValidationService) {
        this.projectService = projectService;
        this.errorValidationService = errorValidationService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return errorValidationService.getErrorResponseMap(bindingResult); // Error Case
        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier){
        Project project = projectService.findProjectByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    @Transactional
    public ResponseEntity<?> deleteByProjectIdentifier(@PathVariable String projectIdentifier)
    {
        projectService.deleteProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with ID" + projectIdentifier + " was successfully deleted", HttpStatus.OK);
    }



}
