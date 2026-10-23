package com.example.abhishek.SprintDesk_Project_Management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProjectRequestDto {


    @Size(min = 1, max = 100)
    private String name;


    @Size(min = 1, max = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
