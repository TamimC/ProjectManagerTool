package com.tamimtechnology.projectmanager.repositories;

import com.tamimtechnology.projectmanager.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByProjectIdentifier(String projectId);
    void deleteByProjectIdentifier(String projectIdentifier);
}
