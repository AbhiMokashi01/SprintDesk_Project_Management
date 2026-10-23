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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFielderrors() {
        return fielderrors;
    }

    public void setFielderrors(Map<String, String> fielderrors) {
        this.fielderrors = fielderrors;
    }

    public ValidationExceptionDto(LocalDateTime dateTime, String message, int statusCode, String error, String path, Map<String, String> fielderrors) {
        this.dateTime = dateTime;
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
        this.fielderrors = fielderrors;


    }
}
