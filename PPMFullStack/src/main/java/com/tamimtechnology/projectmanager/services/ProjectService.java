package com.tamimtechnology.projectmanager.services;

import com.tamimtechnology.projectmanager.exceptions.ProjectIdentifierException;
import com.tamimtechnology.projectmanager.model.Backlog;
import com.tamimtechnology.projectmanager.model.Project;
import com.tamimtechnology.projectmanager.repositories.BacklogRepository;
import com.tamimtechnology.projectmanager.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null){
                project.setBacklog(backlogRepository.findBacklogByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdentifierException("Project ID" + project.getProjectIdentifier().toUpperCase() + " already exists.");
        }
    }

    public Project findProjectByProjectIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) throw new ProjectIdentifierException("Project ID" + projectIdentifier + " does not exist.");
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier){
        if (projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase()) == null) throw new ProjectIdentifierException("Project ID" + projectIdentifier.toUpperCase() + " does not exist.");
        projectRepository.deleteByProjectIdentifier(projectIdentifier.toUpperCase());
    }
}
