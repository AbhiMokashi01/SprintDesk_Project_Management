package com.example.abhishek.SprintDesk_Project_Management.service;


import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Project addProject(Project project) {
        project.setId(UUID.randomUUID().getMostSignificantBits());
        project.setCreatedAt(Instant.now());
        project.setKey(project.getKey().toUpperCase());
        return projectRepository.addProject(project);
    }

    public List<Project> getProjects() {
        return projectRepository.getAllprojects();
    }


    public Project getProjectById(Long id) {
        return projectRepository.getProjectById(id);
    }


    public Project updateProject(Long id, Project project) {

        return projectRepository.updateProject(id,project);
    }

    public String deleteProject(Long id) {
        return projectRepository.deleteProject(id);
    }
}
