package com.example.abhishek.SprintDesk_Project_Management.repository;

import com.example.abhishek.SprintDesk_Project_Management.models.Project;

import java.util.List;

public interface ProjectRepository {
    public Project getProjectById(Long id);
    public List<Project> getAllprojects();
    public Project updateProject(long id,Project project);
    public Project addProject(Project project);
    public String deleteProject(Long id);
}
