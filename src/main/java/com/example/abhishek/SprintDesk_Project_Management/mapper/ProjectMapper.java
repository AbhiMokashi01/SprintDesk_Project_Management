package com.example.abhishek.SprintDesk_Project_Management.mapper;


import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateProjectRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.request.UpdateProjectRequestDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.ProjectResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project dtoToProjectModel(@Valid CreateProjectRequestDto projectRequestDto) {

        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setKey(projectRequestDto.getKey());

        return project;
    }

    public ProjectResponseDto modelToProjectDto(Project savedProject) {
        ProjectResponseDto projectResponseDto=new ProjectResponseDto();
        projectResponseDto.setId(savedProject.getId());
        projectResponseDto.setName(savedProject.getName());
        projectResponseDto.setDescription(savedProject.getDescription());
        projectResponseDto.setKey(savedProject.getKey());
        projectResponseDto.setCreatedAt(savedProject.getCreatedAt());
        projectResponseDto.setUpdatedAt(savedProject.getUpdatedAt());
        return projectResponseDto;


    }

    public Project updateDtoToModel(UpdateProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        return project;
    }
}
