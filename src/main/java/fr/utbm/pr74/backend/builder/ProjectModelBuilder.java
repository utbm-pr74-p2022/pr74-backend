package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectModelBuilder extends AbstractModelBuilder<Project, ProjectModel> {
    private final UserModelBuilder userModelBuilder;

    @Autowired
    public ProjectModelBuilder(UserModelBuilder userModelBuilder) {
        this.userModelBuilder = userModelBuilder;
    }


    @Override
    public Project build(ProjectModel model) {
        var project = new Project();
        project.setName(model.getName());
        project.setUsers(userModelBuilder.buildList(model.getUsers()));
        return project;
    }
}
