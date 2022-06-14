package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.ProjectController;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

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
        var projectModel = instantiateModel(entity);

        projectModel.add(linkTo(methodOn(ProjectController.class).getProjectById(entity.getId())).withSelfRel());

        projectModel.setId(entity.getId());
        projectModel.setName(entity.getName());
        projectModel.setUsers(userModelAssembler.toCollectionModel(entity.getUsers()));
        projectModel.setPriorities(priorityModelAssembler.toCollectionModel(entity.getPriorities()));
        projectModel.setStatuses(statusModelAssembler.toCollectionModel(entity.getStatuses()));
        projectModel.setBacklog(backlogModelAssembler.toModel(entity.getBacklog()));
        projectModel.setSprints(lightSprintModelAssembler.toCollectionModel(entity.getSprints()));
        return projectModel;
    }

}
