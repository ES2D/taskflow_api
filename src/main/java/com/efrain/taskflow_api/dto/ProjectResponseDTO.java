package com.efrain.taskflow_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    private List<TaskSummaryDTO> tasks;
}