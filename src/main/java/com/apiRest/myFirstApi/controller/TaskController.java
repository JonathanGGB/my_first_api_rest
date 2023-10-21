package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.Task;
import com.apiRest.myFirstApi.exception.TaskNotFoundException;
import com.apiRest.myFirstApi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks/{id}&{name}")
    public Task getTask(@PathVariable Long id, @PathVariable String name){
        if(id <= 0){
            throw new TaskNotFoundException("La tarea con el ID: " + id + " no se encontró");
        }
        return taskService.getTask(id);
    }
}
