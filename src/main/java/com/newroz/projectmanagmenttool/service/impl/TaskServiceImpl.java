package com.newroz.projectmanagmenttool.service.impl;

import com.newroz.projectmanagmenttool.dto.taskdto.TaskDto;
import com.newroz.projectmanagmenttool.exception.ResourceNotFoundException;
import com.newroz.projectmanagmenttool.mapper.TaskMapper;
import com.newroz.projectmanagmenttool.model.Project;
import com.newroz.projectmanagmenttool.model.Task;
import com.newroz.projectmanagmenttool.repository.TaskRepository;
import com.newroz.projectmanagmenttool.service.ProjectService;
import com.newroz.projectmanagmenttool.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjectService projectService;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectService = projectService;
    }


    @Override
    public TaskDto create(String projectId, TaskDto taskDto) throws ResourceNotFoundException {
        Project existProject = taskMapper.viewProjectDtoToProject(projectService.getProject(projectId));
        Task newTask = taskMapper.taskDtoToTask(taskDto);
        newTask.setProject(existProject);
        return taskMapper.taskToTaskDto(taskRepository.save(newTask));
    }

    @Override
    public TaskDto update(String projectId, String taskId, TaskDto updateTaskDto) throws ResourceNotFoundException {
        Project existProject = taskMapper.viewProjectDtoToProject(projectService.getProject(projectId));
        Task existTask = fetchTask(taskId);
        existTask = taskMapper.taskDtoToExistTask(updateTaskDto, existTask);
        existTask.setProject(existProject);
        taskRepository.save(existTask);
        return taskMapper.taskToTaskDto(existTask);
    }


    @Override
    public void delete(String projectId, String taskId) throws ResourceNotFoundException {
        projectService.getProject(projectId);
        Task existTask = fetchTask(taskId);
        existTask.getProject().getTasks().remove(existTask);
        taskRepository.delete(existTask);
    }

    private Task fetchTask(String taskId) throws ResourceNotFoundException
    {
        UUID taskUuid = UUID.randomUUID();
        String errorMessage = "Task doesn't exist";
        try{
            taskUuid = UUID.fromString(taskId);
        }catch (IllegalArgumentException ex)
        {
            throw new ResourceNotFoundException(errorMessage);
        }
        return taskRepository.findById(taskUuid).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
