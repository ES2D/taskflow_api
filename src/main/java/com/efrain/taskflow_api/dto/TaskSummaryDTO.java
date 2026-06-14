package com.efrain.taskflow_api.dto;

import com.efrain.taskflow_api.entity.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskSummaryDTO {

    private Long id;
    private String title;
    private TaskStatus status;
}