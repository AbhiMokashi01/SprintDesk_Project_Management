package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateProjectRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.request.UpdateProjectRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.ProjectResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.mapper.ProjectMapper;
import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<ProjectResponseDto>> getProjects()
    {
        List<Project> projects=projectService.getProjects();
        List<ProjectResponseDto> projectResponseDtoList=new ArrayList<>();

        for(Project project:projects)
        {
            projectResponseDtoList.add(projectMapper.modelToProjectDto(project));
        }

        return ResponseEntity.ok(projectResponseDtoList);


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) {

        Project project= projectService.getProjectById(id);
        ProjectResponseDto projectResponseDto=projectMapper.modelToProjectDto(project);
        return ResponseEntity.ok(projectResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @RequestBody UpdateProjectRequestDto projectRequestDto) {

        Project project=projectMapper.updateDtoToModel(projectRequestDto);
        Project updatedProject=projectService.updateProject(id, project);
        ProjectResponseDto projectResponseDto=projectMapper.modelToProjectDto(updatedProject);
        return ResponseEntity.ok(projectResponseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }


}
