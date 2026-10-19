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

        if(project==null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Project is null");
        }
        boolean duplicate=projectRepository.projectExists(project.getKey());

        if(duplicate)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Project already exists");
        }



        project.setId(UUID.randomUUID().getMostSignificantBits());
        project.setCreatedAt(Instant.now());
        project.setKey(project.getKey().toUpperCase());
        return projectRepository.addProject(project);
    }

    public List<Project> getProjects() {
        return projectRepository.getAllprojects();
    }


    public Project getProjectById(Long id) {
        if(id<=0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Project id should be positive");
        if(projectRepository.getProjectById(id)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Project not found");
        return projectRepository.getProjectById(id);
    }


    public Project updateProject(Long id, Project project) {
        if(id<=0 || project==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Project not found");
        if(projectRepository.getProjectById(id)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Project not found");
        return projectRepository.updateProject(id,project);
    }

    public String deleteProject(Long id) {
        if(id<=0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Project id should be positive");
        if(projectRepository.getProjectById(id)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Project not found");

        return projectRepository.deleteProject(id);
    }
}
