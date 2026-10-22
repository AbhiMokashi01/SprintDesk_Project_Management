package com.example.abhishek.SprintDesk_Project_Management.controllers;

import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateTaskRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.TaskResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.mapper.TaskMapper;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController

public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody @Valid CreateTaskRequestDto taskRequestDto, @PathVariable long projectId) {

        Task task=taskMapper.dtoToModel(taskRequestDto);

        Task savedTask=taskService.addTask(task,projectId);

        TaskResponseDto taskResponseDto=taskMapper.modelToDto(savedTask);





        URI location= URI.create("/api/projects/"+projectId+"/tasks"+savedTask.getId());

        return ResponseEntity.created(location)
                .body(taskResponseDto);

    }


    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskResponseDto>>  getTasksByProjectId(@PathVariable long projectId) {
        List<Task> tasksOfProject=taskService.getTasksByProjectId(projectId);

        List<TaskResponseDto> taskResponseDtoList=new ArrayList<>();

        for(Task task:tasksOfProject){
            taskResponseDtoList.add(taskMapper.modelToDto(task));
        }

        return ResponseEntity.ok(taskResponseDtoList);

    }

    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable long taskId) {
        Task task=taskService.getTaskById(taskId);
        TaskResponseDto taskResponseDto=taskMapper.modelToDto(task);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PatchMapping("/api/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,@PathVariable long taskId) {
        return ResponseEntity.ok()
                .body(taskService.updateTask(taskId,task));
    }

    @DeleteMapping("/api/tasks/{taskId}")
    public void deleteTaskById(@PathVariable long taskId) {
        taskService.deleteTaskById(taskId);


    }

}
