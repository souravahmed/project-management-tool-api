package com.newroz.projectmanagmenttool.dto.projectdto;

import com.newroz.projectmanagmenttool.dto.taskdto.TaskDto;

import java.util.Set;
import java.util.UUID;

public class ViewProjectDto {

    private UUID id;
    private String name;
    private String description;
    private String status;
    private Set<TaskDto> tasks;

    public ViewProjectDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
