package com.newroz.projectmanagmenttool.service.impl;

import com.newroz.projectmanagmenttool.dto.projectdto.ProjectDto;
import com.newroz.projectmanagmenttool.dto.projectdto.ViewProjectDto;
import com.newroz.projectmanagmenttool.exception.ResourceNotFoundException;
import com.newroz.projectmanagmenttool.mapper.ProjectMapper;
import com.newroz.projectmanagmenttool.model.Project;
import com.newroz.projectmanagmenttool.repository.ProjectRepository;
import com.newroz.projectmanagmenttool.service.ProjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository repository;
    private ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper projectMapper) {
        this.repository = repository;
        this.projectMapper = projectMapper;
    }



    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectMapper.projectDtoToProject(projectDto);
        project = repository.save(project);
        return projectMapper.projectToProjectDto(project);
    }

    @Override
    public ProjectDto update(String projectId, ProjectDto updateProjectDto) throws ResourceNotFoundException {
        Project existProject = fetchProject(projectId);
        existProject = repository.save(projectMapper.projectDtoToExistProject(updateProjectDto, existProject));
        return  projectMapper.projectToProjectDto(existProject);
    }

    @Override
    public void delete(String projectId) throws ResourceNotFoundException {
        Project existProject = fetchProject(projectId);
        repository.deleteById(existProject.getId());
    }

    @Override
    public List<ProjectDto> getProjects(Pageable pageable) {
        return projectMapper.pageProjectToProjectDtos(repository.findAll(pageable));
    }

    @Override
    public ViewProjectDto getProject(String projectId) throws ResourceNotFoundException {

        return projectMapper.projectToViewProjectDto(fetchProject(projectId));
    }

    private Project fetchProject(String projectId) throws ResourceNotFoundException
    {
        UUID projectUuid = UUID.randomUUID();
        String errorMessage = "Project doesn't exist";
        try{
            projectUuid = UUID.fromString(projectId);
        }catch (IllegalArgumentException ex)
        {
            throw new ResourceNotFoundException(errorMessage);
        }
        return repository.findById(projectUuid).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
