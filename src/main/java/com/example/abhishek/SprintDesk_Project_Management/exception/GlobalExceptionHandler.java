package com.example.abhishek.SprintDesk_Project_Management.exception;


import com.example.abhishek.SprintDesk_Project_Management.dto.response.ExceptionResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.ValidationExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(DuplicateProjectKeyException.class)
public ResponseEntity<ExceptionResponseDto> duplicateProjectKeyHandler(DuplicateProjectKeyException exception, HttpServletRequest request)
{
    ExceptionResponseDto exceptionDto=new ExceptionResponseDto(
            LocalDateTime.now(),exception.getMessage(), HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ExceptionResponseDto> resourceNotFoundHandler(ResourceNotFoundException exception, HttpServletRequest request)
{
   ExceptionResponseDto exceptionDto=new ExceptionResponseDto(
           LocalDateTime.now(),exception.getMessage(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),request.getRequestURI());

   return ResponseEntity.status(HttpStatus.NOT_FOUND)
           .body(exceptionDto);
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ValidationExceptionDto> validationExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request)
{
    Map<String,String> fieldErrors=new HashMap<>();

    List<FieldError> fieldErrorList=exception.getBindingResult().getFieldErrors();
    for(FieldError fieldError:fieldErrorList)
    {
        fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
    }

    ValidationExceptionDto validationExceptionDto=new ValidationExceptionDto(LocalDateTime.now(),exception.getMessage(),
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), request.getRequestURI(),fieldErrors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(validationExceptionDto);
}


@ExceptionHandler(BusinessConflictException.class)
public ResponseEntity<ExceptionResponseDto> businessConflictHandler(BusinessConflictException exception, HttpServletRequest request)
{
    ExceptionResponseDto exceptionDto=new ExceptionResponseDto(
            LocalDateTime.now(),
            exception.getMessage(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(exceptionDto);
}




}
