package com.apiRest.myFirstApi.repository;

import com.apiRest.myFirstApi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
