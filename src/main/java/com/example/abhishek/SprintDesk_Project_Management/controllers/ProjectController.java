package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateProjectRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.ProjectResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.mapper.ProjectMapper;
import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;


    @PostMapping
    public ResponseEntity<ProjectResponseDto> addProject(@RequestBody @Valid CreateProjectRequestDto projectRequestDto) {

        Project project=projectMapper.dtoToProjectModel(projectRequestDto);

        Project savedProject=projectService.addProject(project);

        ProjectResponseDto projectResponseDto=projectMapper.modelToProjectDto(savedProject);

        return ResponseEntity.ok(projectResponseDto);


    }


    @GetMapping
    public List<Project> getProjects()
    {
        return projectService.getProjects();

    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PatchMapping("/{id}")
    public Project updateProject(@PathVariable Long id,@RequestBody Project project) {
        return projectService.updateProject(id,project);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }


}
