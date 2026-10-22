package com.example.abhishek.SprintDesk_Project_Management.dto.request;

import com.example.abhishek.SprintDesk_Project_Management.models.TaskPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

public class CreateTaskRequestDto {

    @NotBlank(message = "Task should have a title")
    @Size(min=3,max = 50)

    private String title;

    @Size(max=500)
    private String description;

    @NotNull
    private TaskPriority priority;

    @Future
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
