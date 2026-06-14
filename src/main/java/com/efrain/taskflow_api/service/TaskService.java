package com.efrain.taskflow_api.service;

import com.efrain.taskflow_api.dto.TaskRequestDTO;
import com.efrain.taskflow_api.dto.TaskResponseDTO;
import com.efrain.taskflow_api.entity.Project;
import com.efrain.taskflow_api.entity.TaskStatus;
import com.efrain.taskflow_api.exception.ResourceNotFoundException;
import com.efrain.taskflow_api.repository.ProjectRepository;
import com.efrain.taskflow_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.efrain.taskflow_api.entity.Task;
import java.util.List;
import java.time.LocalDateTime;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public List<TaskResponseDTO> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public TaskResponseDTO getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return mapToDTO(task);
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) {

        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Task task = new Task();

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setProject(project);
        task.setCreatedAt(LocalDateTime.now());

        if(taskDTO.getStatus() != null){
            task.setStatus(taskDTO.getStatus());
        } else {
            task.setStatus(TaskStatus.PENDIENTE);
        }

        Task savedTask = taskRepository.save(task);

        return mapToDTO(savedTask);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskDTO) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());

        if(taskDTO.getStatus() != null){
            existingTask.setStatus(taskDTO.getStatus());
        }

        if(taskDTO.getProjectId() != null){

            Project project = projectRepository.findById(taskDTO.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            existingTask.setProject(project);
        }

        Task updatedTask = taskRepository.save(existingTask);

        return mapToDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO mapToDTO(Task task){

        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setCreatedAt(task.getCreatedAt());

        dto.setProjectId(task.getProject().getId());
        dto.setProjectName(task.getProject().getName());

        return dto;
    }

}