package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.ProjectController;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.resource.LightProjectModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class LightProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, LightProjectModel> {

    public LightProjectModelAssembler() {
        super(ProjectController.class, LightProjectModel.class);
    }

    @Override
    public LightProjectModel toModel(Project entity) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var projectModel = instantiateModel(entity);

        projectModel.add(linkTo(methodOn(ProjectController.class).getProjectById(entity.getId())).withSelfRel());

        projectModel.setId(entity.getId());
        projectModel.setName(entity.getName());
        projectModel.setDate(formatter.format(entity.getDate()));
        projectModel.setStatus(entity.getStatus());
        return projectModel;
    }
}
