package com.example.abhishek.SprintDesk_Project_Management.controllers;

import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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


    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<Task>>  getTasksByProjectId(@PathVariable long projectId) {
        return ResponseEntity.ok()
                .body(taskService.getTasksByProjectId(projectId));
    }

    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
        return ResponseEntity.ok()
                .body(taskService.getTaskById(taskId));
    }

    @PatchMapping("/api/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,@PathVariable long taskId) {
        return ResponseEntity.ok()
                .body(taskService.updateTask(taskId,task);
    }

    @DeleteMapping("/api/tasks/{taskId}")
    public void deleteTaskById(@PathVariable long taskId) {
        taskService.deleteTaskById(taskId);


    }

}
