package com.efrain.taskflow_api.repository;

import com.efrain.taskflow_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}