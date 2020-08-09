package com.tamimtechnology.projectmanager.repositories;

import com.tamimtechnology.projectmanager.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
