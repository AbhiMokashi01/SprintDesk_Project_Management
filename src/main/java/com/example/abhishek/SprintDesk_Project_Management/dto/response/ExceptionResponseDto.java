package com.example.abhishek.SprintDesk_Project_Management.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExceptionResponseDto {
    private LocalDateTime dateTime;
    private String message;
    private int statusCode;
    private String error;
    private String path;

    public ExceptionResponseDto(LocalDateTime dateTime, String message, int statusCode, String error, String path) {
        this.dateTime = dateTime;
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
    }


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
}
