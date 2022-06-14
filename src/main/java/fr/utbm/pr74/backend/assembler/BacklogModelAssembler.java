package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.BacklogController;
import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.resource.BacklogModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BacklogModelAssembler extends RepresentationModelAssemblerSupport<Backlog, BacklogModel> {
    private final LightProjectModelAssembler lightProjectModelAssembler;
    private final TaskModelAssembler taskModelAssembler;

    public BacklogModelAssembler(LightProjectModelAssembler lightProjectModelAssembler, TaskModelAssembler taskModelAssembler) {
        super(BacklogController.class, BacklogModel.class);
        this.lightProjectModelAssembler = lightProjectModelAssembler;
        this.taskModelAssembler = taskModelAssembler;
    }

    @Override
    public BacklogModel toModel(Backlog entity) {
        var backlogModel = instantiateModel(entity);

        backlogModel.add(linkTo(methodOn(BacklogController.class).getBacklogById(entity.getId())).withSelfRel());

        backlogModel.setId(entity.getId());
        backlogModel.setProject(lightProjectModelAssembler.toModel(entity.getProject()));
        backlogModel.setTasks(taskModelAssembler.toCollectionModel(entity.getTasks()));
        return backlogModel;
    }
}
