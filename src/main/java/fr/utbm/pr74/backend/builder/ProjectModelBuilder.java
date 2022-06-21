package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.ProjectModel;
import fr.utbm.pr74.backend.service.BacklogService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ProjectModelBuilder extends AbstractModelBuilder<Project, ProjectModel> {
    private final UserModelBuilder userModelBuilder;
    private final PriorityModelBuilder priorityModelBuilder;
    private final StatusModelBuilder statusModelBuilder;
    private final BacklogService backlogService;

    @Autowired
    public ProjectModelBuilder(UserModelBuilder userModelBuilder, PriorityModelBuilder priorityModelBuilder, StatusModelBuilder statusModelBuilder, BacklogService backlogService) {
        this.userModelBuilder = userModelBuilder;
        this.priorityModelBuilder = priorityModelBuilder;
        this.statusModelBuilder = statusModelBuilder;
        this.backlogService = backlogService;
    }


    @SneakyThrows
    @Override
    public Project build(ProjectModel model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var project = new Project();
        project.setName(model.getName());
        project.setUsers(userModelBuilder.buildList(model.getUsers()));
        project.setPriorities(priorityModelBuilder.buildList(model.getPriorities()));
        project.setStatuses(statusModelBuilder.buildList(model.getStatuses()));
        if (model.getBacklog() != null && model.getBacklog().getId() != null) {
            project.setBacklog(backlogService.getById(model.getBacklog().getId()).orElseThrow());
        }
        project.setStatus(model.getStatus());
        if (model.getDate() != null) {
            project.setDate(formatter.parse(model.getDate()));
        }
        return project;
    }
}
