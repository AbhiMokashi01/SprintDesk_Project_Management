package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.service.ProjectService;
import com.sun.source.tree.BreakTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        //return projectService.addProject(project);
        URI location =URI.create("/api/projects/" + project.getId());
        return ResponseEntity
                .created(location)
                .body(projectService.addProject(project));

    }


    @GetMapping
    public ResponseEntity<List<Project>> getProjects()
    {
//        return projectService.getProjects();
        return ResponseEntity.ok(projectService.getProjects());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,@RequestBody Project project) {
        Project proj=projectService.updateProject(id,project);
        return ResponseEntity.ok(proj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        System.out.println("Delete Project");
         projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }


}
