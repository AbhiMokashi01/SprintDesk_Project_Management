package com.example.abhishek.SprintDesk_Project_Management.dto.request;

public class CreateProjectRequestDto {

    @NotBlank(me)
    private String name;
    private String description;
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
