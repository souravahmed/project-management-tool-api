package com.newroz.projectmanagmenttool.service;

import com.newroz.projectmanagmenttool.dto.projectdto.ProjectDto;
import com.newroz.projectmanagmenttool.dto.projectdto.ViewProjectDto;
import com.newroz.projectmanagmenttool.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);
    ProjectDto update(String projectId, ProjectDto updateProjectDto);
    void delete(String projectId);
    List<ProjectDto> getProjects(Pageable pageable);
    ViewProjectDto getProject(String projectId) throws ResourceNotFoundException;
}
