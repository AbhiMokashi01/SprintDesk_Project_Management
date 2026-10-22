package com.example.abhishek.SprintDesk_Project_Management.dto.request;

import com.example.abhishek.SprintDesk_Project_Management.models.TaskPriority;

import java.time.LocalDate;

public class UpdateTaskRequestDto {
   private String title;
   private String description;
   private TaskPriority priority;
   private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
