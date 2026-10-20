package com.example.abhishek.SprintDesk_Project_Management.service;

import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.repository.ProjectRepository;
import com.example.abhishek.SprintDesk_Project_Management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        task.setProject(project);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasksByProjectId(long projectId) {
        if(projectRepository.findById(projectId).isPresent()) {
            return taskRepository.findAllByProjectId(projectId);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projects not found with project id"+projectId);
    }
}
