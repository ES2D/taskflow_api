package com.efrain.taskflow_api.service;

import com.efrain.taskflow_api.dto.ProjectRequestDTO;
import com.efrain.taskflow_api.dto.ProjectResponseDTO;
import com.efrain.taskflow_api.dto.TaskSummaryDTO;
import com.efrain.taskflow_api.entity.Project;
import com.efrain.taskflow_api.exception.ResourceNotFoundException;
import com.efrain.taskflow_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponseDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ProjectResponseDTO getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        return mapToDTO(project);
    }

    public ProjectResponseDTO createProject(
            ProjectRequestDTO projectDTO) {

        Project project = new Project();

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setCreatedAt(LocalDateTime.now());

        Project savedProject = projectRepository.save(project);

        return mapToDTO(savedProject);
    }

    public ProjectResponseDTO updateProject(
            Long id,
            ProjectRequestDTO projectDTO) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());

        Project updatedProject = projectRepository.save(project);

        return mapToDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectResponseDTO mapToDTO(Project project) {

        ProjectResponseDTO dto = new ProjectResponseDTO();

        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setCreatedAt(project.getCreatedAt());

        List<TaskSummaryDTO> tasks = project.getTasks()
                .stream()
                .map(task -> {

                    TaskSummaryDTO taskDTO = new TaskSummaryDTO();

                    taskDTO.setId(task.getId());
                    taskDTO.setTitle(task.getTitle());
                    taskDTO.setStatus(task.getStatus());

                    return taskDTO;
                })
                .toList();

        dto.setTasks(tasks);

        return dto;
    }
}