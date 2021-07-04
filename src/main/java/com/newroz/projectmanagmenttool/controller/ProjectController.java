package com.newroz.projectmanagmenttool.controller;


import com.newroz.projectmanagmenttool.dto.projectdto.ProjectDto;
import com.newroz.projectmanagmenttool.dto.projectdto.ViewProjectDto;
import com.newroz.projectmanagmenttool.exception.ResourceNotFoundException;
import com.newroz.projectmanagmenttool.service.ProjectService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getProjects(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
                                                        @RequestParam Optional<Sort.Direction> orderBy)
    {

        Pageable pageable = PageRequest.of(page.orElse(0), 10, orderBy.orElse(Sort.Direction.ASC), sortBy.orElse("id"));
        return new ResponseEntity<>(projectService.getProjects(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewProjectDto> getProject(@PathVariable("id") String projectId) throws ResourceNotFoundException
    {
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto createProjectDto)
    {
        return new ResponseEntity<>(projectService.create(createProjectDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") String projectId, @Valid @RequestBody ProjectDto projectDto) throws ResourceNotFoundException
    {
        return new ResponseEntity<>(projectService.update(projectId, projectDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProject(@PathVariable("id") String projectId) throws ResourceNotFoundException
    {
        projectService.delete(projectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
