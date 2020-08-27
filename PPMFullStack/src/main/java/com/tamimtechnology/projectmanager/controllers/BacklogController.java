package com.tamimtechnology.projectmanager.controllers;

import com.tamimtechnology.projectmanager.model.ProjectTask;
import com.tamimtechnology.projectmanager.services.ErrorValidationService;
import com.tamimtechnology.projectmanager.services.ProjectTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    private final ProjectTaskService projectTaskService;
    private final ErrorValidationService errorValidationService;

    public BacklogController(ProjectTaskService projectTaskService, ErrorValidationService errorValidationService) {
        this.projectTaskService = projectTaskService;
        this.errorValidationService = errorValidationService;
    }

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                                     @PathVariable String backlog_id){
        ResponseEntity<?> errorMap = errorValidationService.getErrorResponseMap(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<List<ProjectTask>> getListOfProjectTasks(@PathVariable String backlog_id){
        return new ResponseEntity<List<ProjectTask>>(projectTaskService.findAllTasksByProjectIdentifier(backlog_id), HttpStatus.OK);
    }

    @GetMapping("{backlog_id}/{projectTask_sequence}")
    public ResponseEntity<ProjectTask> getProjectTaskByProjectTaskSequence(@PathVariable String backlog_id, @PathVariable String projectTask_sequence){
        return new ResponseEntity<ProjectTask>(projectTaskService.getProjectTaskByProjectTaskSequence(backlog_id, projectTask_sequence), HttpStatus.OK);
    }

    @PutMapping("{backlog_id}/{projectTask_sequence}")
    public ResponseEntity<ProjectTask> updateProjectTask(@PathVariable String backlog_id, @PathVariable String projectTask_sequence, @Valid @RequestBody ProjectTask updatedProjectTask){
        return new ResponseEntity<ProjectTask>(projectTaskService.updateProjectTask(backlog_id, projectTask_sequence, updatedProjectTask), HttpStatus.OK);
    }
}
