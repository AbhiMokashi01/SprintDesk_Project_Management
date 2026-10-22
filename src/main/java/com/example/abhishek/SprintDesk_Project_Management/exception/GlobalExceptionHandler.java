package com.example.abhishek.SprintDesk_Project_Management.exception;


import com.example.abhishek.SprintDesk_Project_Management.dto.response.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(DuplicateProjectKeyException.class)
public ResponseEntity<ExceptionResponseDto> duplicateProjectKeyHandler(RuntimeException exception, HttpServletRequest request)
{
    ExceptionResponseDto exceptionDto=new ExceptionResponseDto(
            LocalDateTime.now(),exception.getMessage(), HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ExceptionResponseDto> resourceNotFoundHandler(RuntimeException exception, HttpServletRequest request)
{
   ExceptionResponseDto exceptionDto=new ExceptionResponseDto(
           LocalDateTime.now(),exception.getMessage(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),request.getRequestURI());

   return ResponseEntity.status(HttpStatus.NOT_FOUND)
           .body(exceptionDto);
}



}
