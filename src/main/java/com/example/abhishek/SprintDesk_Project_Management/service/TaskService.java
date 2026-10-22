package com.example.abhishek.SprintDesk_Project_Management.service;

import com.example.abhishek.SprintDesk_Project_Management.exception.BusinessConflictException;
import com.example.abhishek.SprintDesk_Project_Management.exception.ResourceNotFoundException;
import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.models.TaskStatus;
import com.example.abhishek.SprintDesk_Project_Management.repository.ProjectRepository;
import com.example.abhishek.SprintDesk_Project_Management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    public Task addTask(Task task, long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new ResourceNotFoundException("Project with id " + projectId + " not found");
        }
        task.setProject(project);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasksByProjectId(long projectId) {
        if(projectRepository.findById(projectId).isPresent()) {
            return taskRepository.findAllByProjectId(projectId);
        }
        else
            throw new ResourceNotFoundException("Projects not found with project id"+projectId);
    }

    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task not found with id -"+taskId));
    }

    public Task updateTask(long taskId, Task updatedTask) {
        // 1. Fetch existing task
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Task not found with ID: " + taskId
                        )
                );

        // 2. Update only allowed fields
        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }

        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if (updatedTask.getPriority() != null) {
            existingTask.setPriority(updatedTask.getPriority());
        }

        if (updatedTask.getDueDate() != null) {
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        // 3. Update timestamp
        existingTask.setUpdatedAt(Instant.now());

        // 4. Save existing task
        return taskRepository.save(existingTask);
    }

    public void deleteTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if(taskRepository.findById(taskId).isPresent()) {
            if(task.getStatus()== TaskStatus.TODO)
                throw new BusinessConflictException("Task with TaskId- "+taskId+" cant be deleted as the status is still TODO");
            else
                taskRepository.delete(task);
        }
        else
        {
            throw new ResourceNotFoundException("Task not found with id -"+taskId);
        }
    }
}
