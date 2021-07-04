package com.newroz.projectmanagmenttool.mapper;

import com.newroz.projectmanagmenttool.dto.projectdto.ProjectDto;
import com.newroz.projectmanagmenttool.dto.projectdto.ViewProjectDto;
import com.newroz.projectmanagmenttool.enums.Status;
import com.newroz.projectmanagmenttool.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target = "status", qualifiedByName = "getDisplayStatus")
    ViewProjectDto projectToViewProjectDto(Project project);
    List<ProjectDto> pageProjectToProjectDtos(Page<Project> projects);
    @Mapping(target = "id", ignore = true)
    Project projectDtoToProject(ProjectDto projectDto);
    @Mapping(target = "id", ignore = true)
    Project projectDtoToExistProject(ProjectDto dto, @MappingTarget Project project);
    ProjectDto projectToProjectDto(Project project);

    @Named("getDisplayStatus")
    static String getStatusDisplayName(Status status)
    {
        return status.getDisplayStatus();
    }
}
