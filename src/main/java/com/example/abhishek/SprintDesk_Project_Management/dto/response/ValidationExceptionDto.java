package com.example.abhishek.SprintDesk_Project_Management.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionDto {
    private LocalDateTime dateTime;
    private String message;
    private int statusCode;
    private String error;
    private String path;
    private Map<String,String> fielderrors;

    public ValidationExceptionDto(LocalDateTime dateTime, String message, int statusCode, String error, String path, Map<String, String> fielderrors) {
        this.dateTime = dateTime;
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
        this.fielderrors = fielderrors;
    }
}
