package com.newroz.projectmanagmenttool.controller;


import com.newroz.projectmanagmenttool.dto.taskdto.TaskDto;
import com.newroz.projectmanagmenttool.exception.ResourceNotFoundException;
import com.newroz.projectmanagmenttool.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/projects")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> createTask(@PathVariable String projectId, @Valid @RequestBody TaskDto taskDto) throws ResourceNotFoundException
    {
        return new ResponseEntity<>(taskService.create(projectId, taskDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{projectId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> updateTask(@PathVariable String projectId, @PathVariable String taskId, @Valid @RequestBody TaskDto updateTaskTdo)
            throws ResourceNotFoundException
    {
        return new ResponseEntity<>(taskService.update(projectId, taskId, updateTaskTdo), HttpStatus.OK);
    }

    @DeleteMapping(path = "{projectId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@PathVariable String projectId, @PathVariable String taskId) throws ResourceNotFoundException
    {
        taskService.delete(projectId, taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
