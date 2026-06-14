package com.efrain.taskflow_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestDTO {

    @NotBlank
    private String name;

    private String description;
}