package com.apiRest.myFirstApi.service;

import com.apiRest.myFirstApi.entity.Task;
import com.apiRest.myFirstApi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task getTask(Long id){
        return this.taskRepository.findById(id).orElse(null);
    }
}
