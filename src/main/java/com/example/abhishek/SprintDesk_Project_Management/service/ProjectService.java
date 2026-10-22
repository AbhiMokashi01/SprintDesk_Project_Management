package com.example.abhishek.SprintDesk_Project_Management.service;


import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Project addProject(Project project) {
        if(projectRepository.existsByKey(project.getKey())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Project with key-"+project.getKey()+" already exists");
        }

        project.setCreatedAt(Instant.now());
        project.setUpdatedAt(project.getCreatedAt());
        project.setKey(project.getKey().toUpperCase());
        return projectRepository.save(project);

    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id-"+id+" not found"));

    }


    public Project updateProject(Long id, Project project) {
        if(projectRepository.existsById(id)) {
            project.setUpdatedAt(Instant.now());
            return projectRepository.save(project);
            }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id-"+id+" not found");
    }


    public void deleteProjectById(Long id) {
        if(projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id-"+id+" not found");
    }
}
