package com.example.abhishek.SprintDesk_Project_Management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PathVariable;

public class CreateProjectRequestDto {

    @NotBlank(message = "Project Name is Required")
    @Size(min = 5,max = 60)
    private String name;

    @Size(max = 500)
    private String description;

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{2,10}")
    private String key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



}
