package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.ProjectController;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.LightProjectModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class LightProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, LightProjectModel> {

    public LightProjectModelAssembler() {
        super(ProjectController.class, LightProjectModel.class);
    }

    @Override
    public LightProjectModel toModel(Project entity) {
        var projectModel = instantiateModel(entity);

        projectModel.add(linkTo(methodOn(ProjectController.class).getProjectById(entity.getId())).withSelfRel());

        projectModel.setId(entity.getId());
        projectModel.setName(entity.getName());
        return projectModel;
    }
}
