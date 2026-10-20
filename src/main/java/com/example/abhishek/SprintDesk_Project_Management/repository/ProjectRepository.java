package com.example.abhishek.SprintDesk_Project_Management.repository;

import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
//    public Project getProjectById(Long id);
//    public List<Project> getAllprojects();
//    public Project updateProject(long id,Project project);
//    public Project addProject(Project project);
//    public String deleteProject(Long id);

    boolean existsByKey(String key);


}
