package com.newroz.projectmanagmenttool.mapper;


import com.newroz.projectmanagmenttool.dto.projectdto.ViewProjectDto;
import com.newroz.projectmanagmenttool.dto.taskdto.TaskDto;
import com.newroz.projectmanagmenttool.enums.Status;
import com.newroz.projectmanagmenttool.model.Project;
import com.newroz.projectmanagmenttool.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "status", qualifiedByName = "getDisplayStatus")
    TaskDto taskToTaskDto(Task task);
    List<TaskDto> pageTaskDtoToTaskDtos(Page<Task> tasks);

    @Mapping(target = "id", ignore = true)
    Task taskDtoToTask(TaskDto taskDto);
    @Mapping(target = "id", ignore = true)
    Task taskDtoToExistTask(TaskDto dto, @MappingTarget Task task);

    @Mapping(target = "status", qualifiedByName = "getStatus")
    Project viewProjectDtoToProject(ViewProjectDto viewProjectDto);

    @Named("getStatus")
    static Status getStatus(String status)
    {
        return (Status)Enum.valueOf(Status.class, status.toUpperCase());
    }

    @Named("getDisplayStatus")
    static String getStatusDisplayName(Status status)
    {
        return status.getDisplayStatus();
    }
}
