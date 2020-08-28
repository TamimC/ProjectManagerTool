package com.tamimtechnology.projectmanager.services;

import com.tamimtechnology.projectmanager.exceptions.BacklogNotFoundException;
import com.tamimtechnology.projectmanager.exceptions.ProjectNotFoundException;
import com.tamimtechnology.projectmanager.exceptions.ProjectTaskNotFoundException;
import com.tamimtechnology.projectmanager.model.Backlog;
import com.tamimtechnology.projectmanager.model.Project;
import com.tamimtechnology.projectmanager.model.ProjectTask;
import com.tamimtechnology.projectmanager.repositories.BacklogRepository;
import com.tamimtechnology.projectmanager.repositories.ProjectRepository;
import com.tamimtechnology.projectmanager.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectRepository projectRepository;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository, ProjectRepository projectRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
        this.projectRepository = projectRepository;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try{
            Backlog backlog = backlogRepository.findBacklogByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            backlog.setPTSequence(backlog.getPTSequence() + 1);
            projectTask.setProjectSequence(projectIdentifier + "-" + backlog.getPTSequence());
            projectTask.setProjectIdentifier(projectIdentifier);
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) projectTask.setPriority(3);
            if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) projectTask.setStatus("TO_DO");
        }catch(Exception e){
            throw new ProjectNotFoundException("Project with project identifier " + projectIdentifier + " not found.");
        }

        return projectTaskRepository.save(projectTask);
    }

    public List<ProjectTask> findAllTasksByProjectIdentifier(String projectIdentifier){
        if (projectRepository.findByProjectIdentifier(projectIdentifier) == null)
            throw new ProjectNotFoundException("Project with id " + projectIdentifier + " not found");
        return projectTaskRepository.findAllByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    public ProjectTask getProjectTaskByProjectTaskSequence(String backlog_id, String projectTaskSequence){
        ProjectTask desiredTask = validateProjectTaskSequence(backlog_id, projectTaskSequence);
        return desiredTask;
    }

    public ProjectTask updateProjectTask(String backlog_id, String projectTaskSequence, ProjectTask updatedProjectTask){
        ProjectTask desiredTask = validateProjectTaskSequence(backlog_id, projectTaskSequence);
        desiredTask = updatedProjectTask;

        return projectTaskRepository.save(desiredTask);
    }

    public void deleteProjectTask(String backlog_id, String projectTaskSequence){
        ProjectTask desiredTask = validateProjectTaskSequence(backlog_id, projectTaskSequence);
        projectTaskRepository.delete(desiredTask);
    }

    // Helper Method
    public ProjectTask validateProjectTaskSequence(String backlog_id, String projectTaskSequence){
        Backlog backlog = backlogRepository.findBacklogByProjectIdentifier(backlog_id);
        if (backlog == null){
            throw new BacklogNotFoundException("There does not exist a backlog with id " + backlog_id + ".");
        }

        ProjectTask desiredTask = null;
        for(ProjectTask task : backlog.getProjectTaskList()){
            if (task.getProjectSequence().equals(projectTaskSequence)) desiredTask = task;
        }

        if (desiredTask == null)
            throw new ProjectTaskNotFoundException("There does not exist a project task with project sequence " + projectTaskSequence + " in backlog " + backlog_id);


        return desiredTask;
    }

}
