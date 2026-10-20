package com.example.abhishek.SprintDesk_Project_Management.controllers;

import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController

public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task, @PathVariable long projectId) {
        URI location= URI.create("/api/projects/"+projectId+"/tasks");

        return ResponseEntity.created(location)
                .body(taskService.addTask(task,projectId));

    }
}
