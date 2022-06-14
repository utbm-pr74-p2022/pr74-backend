package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.BacklogController;
import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.resource.LightBacklogModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LightBacklogModelAssembler extends RepresentationModelAssemblerSupport<Backlog, LightBacklogModel> {
    private final LightProjectModelAssembler lightProjectModelAssembler;

    public LightBacklogModelAssembler(LightProjectModelAssembler lightProjectModelAssembler) {
        super(BacklogController.class, LightBacklogModel.class);
        this.lightProjectModelAssembler = lightProjectModelAssembler;
    }

    @Override
    public LightBacklogModel toModel(Backlog entity) {
        var backlogModel = instantiateModel(entity);

        backlogModel.add(linkTo(methodOn(BacklogController.class).getBacklogById(entity.getId())).withSelfRel());

        backlogModel.setId(entity.getId());
        if (entity.getProject() != null) {
            backlogModel.setProject(lightProjectModelAssembler.toModel(entity.getProject()));
        }
        return backlogModel;
    }
}
