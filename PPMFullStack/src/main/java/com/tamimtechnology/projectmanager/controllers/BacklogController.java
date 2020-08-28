package com.tamimtechnology.projectmanager.controllers;

import com.tamimtechnology.projectmanager.model.ProjectTask;
import com.tamimtechnology.projectmanager.services.ErrorValidationService;
import com.tamimtechnology.projectmanager.services.ProjectTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
        if (errorValidationService.getErrorResponseMap(result) != null) return errorValidationService.getErrorResponseMap(result);

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<List<ProjectTask>> getListOfProjectTasks(@PathVariable String backlog_id){
        return new ResponseEntity<>(projectTaskService.findAllTasksByProjectIdentifier(backlog_id), HttpStatus.OK);
    }

    @GetMapping("{backlog_id}/{projectTask_sequence}")
    public ResponseEntity<ProjectTask> getProjectTaskByProjectTaskSequence(@PathVariable String backlog_id, @PathVariable String projectTask_sequence){
        return new ResponseEntity<ProjectTask>(projectTaskService.getProjectTaskByProjectTaskSequence(backlog_id, projectTask_sequence), HttpStatus.OK);
    }

    @PatchMapping("{backlog_id}/{projectTask_sequence}")
    public ResponseEntity<?> updateProjectTask(@PathVariable String backlog_id,
                                                         @PathVariable String projectTask_sequence,
                                                         @Valid @RequestBody ProjectTask updatedProjectTask,
                                                         BindingResult result)
    {
        if (errorValidationService.getErrorResponseMap(result) != null) return errorValidationService.getErrorResponseMap(result);
        return new ResponseEntity<>(projectTaskService.updateProjectTask(backlog_id, projectTask_sequence, updatedProjectTask), HttpStatus.OK);
    }

    @DeleteMapping("{backlog_id}/{projectTask_sequence}")
    @Transactional
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id,
                                               @PathVariable String projectTask_sequence){
        projectTaskService.deleteProjectTask(backlog_id, projectTask_sequence);
        return new ResponseEntity<String>("Project Task with backlog ID" + backlog_id + " and Project Task Sequence " + projectTask_sequence + " was successfully deleted", HttpStatus.OK);
    }
}
