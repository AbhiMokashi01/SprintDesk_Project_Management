package com.example.abhishek.SprintDesk_Project_Management.dto.request;

import com.example.abhishek.SprintDesk_Project_Management.models.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskStatusRequestDto {

    @NotNull(message = "Task status required")
    private TaskStatus taskStatus;

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
