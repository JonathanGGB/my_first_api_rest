package com.apiRest.myFirstApi.exceptionHandler;

import com.apiRest.myFirstApi.controller.TaskController;
import com.apiRest.myFirstApi.exception.TaskNotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //GLOBAL
//@RestControllerAdvice("com.apiRest.myFirstApi.controller") //Por paquetes
//@RestControllerAdvice(assignableTypes = TaskController.class) //Por clase
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException ex){
        return new ErrorResponse("Tarea no encontrada", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRuntimeException(TaskNotFoundException ex){
        return new ErrorResponse("Tarea no encontrada", ex.getMessage());
    }

    @JsonInclude
    @Data
    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message){
            this.error = error;
            this.message = message;
        }
    }
}
