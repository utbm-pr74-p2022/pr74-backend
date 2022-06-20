package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.ProjectController;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectModel> {
    private final UserModelAssembler userModelAssembler;
    private final PriorityModelAssembler priorityModelAssembler;
    private final StatusModelAssembler statusModelAssembler;
    private final LightSprintModelAssembler lightSprintModelAssembler;
    private final BacklogModelAssembler backlogModelAssembler;

    @Autowired
    public ProjectModelAssembler(UserModelAssembler userModelAssembler, PriorityModelAssembler priorityModelAssembler, StatusModelAssembler statusModelAssembler, LightSprintModelAssembler lightSprintModelAssembler, BacklogModelAssembler backlogModelAssembler) {
        super(ProjectController.class, ProjectModel.class);
        this.userModelAssembler = userModelAssembler;
        this.priorityModelAssembler = priorityModelAssembler;
        this.statusModelAssembler = statusModelAssembler;
        this.lightSprintModelAssembler = lightSprintModelAssembler;
        this.backlogModelAssembler = backlogModelAssembler;
    }

    @Override
    public ProjectModel toModel(Project entity) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var projectModel = instantiateModel(entity);

        projectModel.add(linkTo(methodOn(ProjectController.class).getProjectById(entity.getId())).withSelfRel());

        projectModel.setId(entity.getId());
        projectModel.setName(entity.getName());
        if (entity.getUsers() != null) {
            projectModel.setUsers(userModelAssembler.toCollectionModel(entity.getUsers()));
        }
        if (entity.getPriorities() != null) {
            projectModel.setPriorities(priorityModelAssembler.toCollectionModel(entity.getPriorities()));
        }
        if (entity.getStatuses() != null) {
            projectModel.setStatuses(statusModelAssembler.toCollectionModel(entity.getStatuses()));
        }
        if (entity.getBacklog() != null) {
            projectModel.setBacklog(backlogModelAssembler.toModel(entity.getBacklog()));
        }
        if (entity.getSprints() != null) {
            projectModel.setSprints(lightSprintModelAssembler.toCollectionModel(entity.getSprints()));
        }
        if (entity.getDate() != null) {
            projectModel.setDate(formatter.format(entity.getDate()));
        }
        projectModel.setStatus(entity.getStatus());
        return projectModel;
    }

}
