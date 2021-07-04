package com.newroz.projectmanagmenttool.service;

import com.newroz.projectmanagmenttool.dto.taskdto.TaskDto;

public interface TaskService {
    TaskDto create(String projectId, TaskDto taskDto);
    TaskDto update(String projectId, String taskId, TaskDto updateTaskDto);
    void delete(String projectId, String taskId);
}
