package com.example.abhishek.SprintDesk_Project_Management.service;


import com.example.abhishek.SprintDesk_Project_Management.exception.BusinessConflictException;
import com.example.abhishek.SprintDesk_Project_Management.exception.DuplicateProjectKeyException;
import com.example.abhishek.SprintDesk_Project_Management.exception.ResourceNotFoundException;
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
        project.setKey(project.getKey().toUpperCase());
        if(projectRepository.existsByKey(project.getKey())) {
            throw new DuplicateProjectKeyException("Project Key - "+ project.getKey()+" already exists");
        }

        project.setCreatedAt(Instant.now());
        project.setUpdatedAt(project.getCreatedAt());

        return projectRepository.save(project);

    }

    public List<Project> getProjects() {
        List<Project> projects=projectRepository.findAll();
        if(projects.isEmpty()) {
            throw new ResourceNotFoundException("No Projects found");
        }
        return projects;
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Project with id- "+id+" not found"));

    }


    public Project updateProject(Long id, Project updated) {

        Project existing = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found: " + id
                        )
                );

        if (updated.getName() != null) {
            existing.setName(updated.getName());
        }

        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }

        existing.setUpdatedAt(Instant.now());

        return projectRepository.save(existing);
    }


    public void deleteProjectById(Long id) {
        if(projectRepository.existsById(id)) {
            if(!projectRepository.findAllTasksByProjectId(id).isEmpty()) {
                throw new BusinessConflictException("Project with id- "+id+" cannot be deleted as it contains tasks");
            }
            else {
                projectRepository.deleteById(id);
            }
        }
        else
            throw new ResourceNotFoundException("Project with id-"+id+" not found");
    }
}
