package com.apiRest.myFirstApi.exceptionHandler;

import com.apiRest.myFirstApi.exception.ErrorResponse;
import com.apiRest.myFirstApi.exception.TaskNotFoundException;
import com.apiRest.myFirstApi.exception.UserNotFoundException;
import com.apiRest.myFirstApi.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //GLOBAL
//@RestControllerAdvice("com.apiRest.myFirstApi.controller") //Por paquetes
//@RestControllerAdvice(assignableTypes = TaskController.class) //Por clase
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException ex){
        return new ErrorResponse("Tarea no encontrada", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex){
        //TO DO
        return new ErrorResponse("Usuario no encontrado", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ValidationErrorResponse("Validación fallida", errorMessages);
    }
}
