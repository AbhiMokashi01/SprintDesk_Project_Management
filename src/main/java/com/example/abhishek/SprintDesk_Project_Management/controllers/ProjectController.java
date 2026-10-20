package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);

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
    public String deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }


}
