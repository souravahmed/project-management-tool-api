package com.newroz.projectmanagmenttool.dto.projectdto;


import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class ProjectDto {
    private UUID id;
    @NotEmpty(message = "Please provide name")
    private String name;
    private String description;
    @NotEmpty(message = "Please provide status")
    private String status;

    public ProjectDto(){}

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
}
