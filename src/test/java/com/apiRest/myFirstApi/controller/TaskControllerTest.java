package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.Task;
import com.apiRest.myFirstApi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void getTaskSuccess() throws Exception{
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);

        given(taskService.getTask(taskId)).willReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getTaskFailed() throws Exception{
        Long taskId = 0L;
        given(taskService.getTask(taskId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createTaskSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Sample task\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTaskError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
