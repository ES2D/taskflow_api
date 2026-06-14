package com.efrain.taskflow_api.repository;

import com.efrain.taskflow_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}