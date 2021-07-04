package com.newroz.projectmanagmenttool.bootstrap;

import com.newroz.projectmanagmenttool.enums.Status;
import com.newroz.projectmanagmenttool.model.Project;
import com.newroz.projectmanagmenttool.model.Task;
import com.newroz.projectmanagmenttool.repository.ProjectRepository;
import com.newroz.projectmanagmenttool.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class LoadDummyData implements CommandLineRunner {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;


    public LoadDummyData(ProjectRepository projectRepository,TaskRepository taskRepository) {

        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        Project projectXray = new Project("Project X-Ray", "this is project x-ray", Status.COMPLETED);
        projectRepository.save(projectXray);

        var task = new Task("lorem imsum dorlo", Status.INPROGRESS);
        task.setProject(projectXray);
        taskRepository.save(task);

        Project projectAqua = new Project("Project Aqua", "this is project Aqua", Status.INPROGRESS);
        projectRepository.save(projectAqua);

        Project projectMars = new Project("Project mars", "this is project mars", Status.INPROGRESS);
        projectRepository.save(projectMars);

        Project projectSun = new Project("Project Sun", "this is project sun", Status.INPROGRESS);
        projectRepository.save(projectSun);

        IntStream.range(5, 21).forEach(range -> {
            Project project = new Project("Project "+ range, "this is project "+ range, Status.INPROGRESS);
            projectRepository.save(project);
            IntStream.range(1, 5).forEach(taskRange -> {
                Task dummyTask = new Task("lorem imsum dorlo "+ taskRange, Status.CREATED);
                dummyTask.setProject(project);
                taskRepository.save(dummyTask);
            });
        });

        System.out.println(String.format("total %s projects created", projectRepository.count()));
        System.out.println(String.format("total %s tasks created", taskRepository.count()));


    }
}
