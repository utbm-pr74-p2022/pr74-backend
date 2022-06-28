package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.ProjectModel;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.BacklogService;
import fr.utbm.pr74.backend.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectModelBuilder extends AbstractModelBuilder<Project, ProjectModel> {
    private final UserService userService;
    private final PriorityModelBuilder priorityModelBuilder;
    private final StatusModelBuilder statusModelBuilder;
    private final BacklogService backlogService;

    @Autowired
    public ProjectModelBuilder(UserService userService, PriorityModelBuilder priorityModelBuilder, StatusModelBuilder statusModelBuilder, BacklogService backlogService) {
        this.userService = userService;
        this.priorityModelBuilder = priorityModelBuilder;
        this.statusModelBuilder = statusModelBuilder;
        this.backlogService = backlogService;
    }

    private List<User> getUsers(CollectionModel<UserModel> userModels){
        List<User> users = new ArrayList<>();
        if(userModels != null) {
            userModels.forEach(userModel ->
                users.add(userService.getById(userModel.getId()).orElseThrow())
            );
        }
        return users;
    }
    @SneakyThrows
    @Override
    public Project build(ProjectModel model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var project = new Project();
        project.setName(model.getName());
        project.setUsers(getUsers(model.getUsers()));
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
