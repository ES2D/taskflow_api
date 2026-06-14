package com.efrain.taskflow_api.dto;

import com.efrain.taskflow_api.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank
    private String title;

    private String description;

    private TaskStatus status;

    @NotNull
    private Long projectId;
}