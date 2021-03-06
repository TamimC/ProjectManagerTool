package com.tamimtechnology.projectmanager.repositories;

import com.tamimtechnology.projectmanager.model.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Backlog findBacklogByProjectIdentifier(String projectIdentifier);
}
