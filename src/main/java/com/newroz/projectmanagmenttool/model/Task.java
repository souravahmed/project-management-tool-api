package com.newroz.projectmanagmenttool.model;


import com.newroz.projectmanagmenttool.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends Base{

    private String name;

    @ManyToOne()
    private Project project;

    @Enumerated(EnumType.STRING)
    private Status status;


    public Task(){}

    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
