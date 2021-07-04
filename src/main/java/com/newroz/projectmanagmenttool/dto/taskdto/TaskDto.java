package com.newroz.projectmanagmenttool.dto.taskdto;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class TaskDto {

    private UUID id;
    @NotEmpty(message = "Please provide name")
    private String name;
    @NotEmpty(message = "Please provide status")
    private String status;


    public TaskDto() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
