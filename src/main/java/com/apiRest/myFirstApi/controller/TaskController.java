package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.Task;
import com.apiRest.myFirstApi.exception.TaskNotFoundException;
import com.apiRest.myFirstApi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id){
        if(id <= 0){
            throw new TaskNotFoundException("La tarea con el ID: " + id + " no se encontró");
        }
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task){
        return taskService.save(task);
    }
}
