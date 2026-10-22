package com.example.abhishek.SprintDesk_Project_Management.mapper;

import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateTaskRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.request.UpdateTaskRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.TaskResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {
    public Task dtoToModel(@Valid CreateTaskRequestDto taskRequestDto) {
        Task task=new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setPriority(taskRequestDto.getPriority());
        task.setDueDate(taskRequestDto.getDueDate());
        return task;
    }

    public TaskResponseDto modelToDto(Task savedTask) {
        TaskResponseDto taskResponseDto=new TaskResponseDto();
        taskResponseDto.setId(savedTask.getId());
        taskResponseDto.setTitle(savedTask.getTitle());
        taskResponseDto.setDescription(savedTask.getDescription());
        taskResponseDto.setAssigneeId(savedTask.getAssigneeId());
        taskResponseDto.setDueDate(savedTask.getDueDate());
        taskResponseDto.setTaskPriority(savedTask.getPriority());
        taskResponseDto.setTaskStatus(savedTask.getStatus());
        taskResponseDto.setCreatedAt(savedTask.getCreatedAt());
        taskResponseDto.setUpdatedAt(savedTask.getUpdatedAt());
        return taskResponseDto;
    }

    public Task UpdateDtoToModel(@Valid UpdateTaskRequestDto taskRequestDto) {
        Task task=new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setPriority(taskRequestDto.getPriority());
        task.setDueDate(taskRequestDto.getDueDate());
        return task;
    }
}
