package com.example.abhishek.SprintDesk_Project_Management.repository;

import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.*;


@Repository
public class InMemoryProjectRepository implements ProjectRepository {

    HashMap<Long, Project> projects;

    public InMemoryProjectRepository() {
        projects = new HashMap<>();
    }

    @Override
    public List<Project> getAllprojects()
    {
        List<Project> projectList = new ArrayList<>();
        for(long id : projects.keySet())
        {
            projectList.add(projects.get(id));
        }
        return projectList;
    }

    @Override
    public Project getProjectById(Long id)
    {
        return projects.get(id);
    }

    @Override
    public Project addProject(Project project)
    {
        projects.put(project.getId(), project);
        return project;
    }

    @Override
    public Project updateProject(long id,Project project)
    {
        Project projectBuffer=projects.get(id);
        if(project.getName()!=null)
        {
            projectBuffer.setName(project.getName());
        }
        if(project.getKey()!=null)
            {
            projectBuffer.setKey(project.getKey());
            }
        if(project.getDescription()!=null)
            {
            projectBuffer.setDescription(project.getDescription());
            }
        if(project.getCreatedAt()!=null)
            {
            projectBuffer.setCreatedAt(project.getCreatedAt());
            }
        projectBuffer.setId(id);
        projects.put(id, projectBuffer);
        return projects.get(id);
    }

    @Override
    public String deleteProject(Long id)
    {
        if(projects.containsKey(id))
        {
            projects.remove(id);
            return "Project with id " + id + " has been deleted";
        }
        else
        {
            return "Project with id " + id + " does not exist";
                        }

    }
}
