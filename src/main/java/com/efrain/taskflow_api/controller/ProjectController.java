package com.efrain.taskflow_api.controller;

import com.efrain.taskflow_api.dto.ProjectRequestDTO;
import com.efrain.taskflow_api.dto.ProjectResponseDTO;
import com.efrain.taskflow_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectResponseDTO createProject(
            @Valid @RequestBody ProjectRequestDTO projectDTO) {

        return projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDTO projectDTO) {

        return projectService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}