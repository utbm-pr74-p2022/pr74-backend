package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.SprintController;
import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.SprintModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SprintModelAssembler extends RepresentationModelAssemblerSupport<Sprint, SprintModel> {
    private final LightProjectModelAssembler lightProjectModelAssembler;
    private final TaskModelAssembler taskModelAssembler;

    public SprintModelAssembler(LightProjectModelAssembler lightProjectModelAssembler, TaskModelAssembler taskModelAssembler) {
        super(SprintController.class, SprintModel.class);
        this.lightProjectModelAssembler = lightProjectModelAssembler;
        this.taskModelAssembler = taskModelAssembler;
    }

    @Override
    public SprintModel toModel(Sprint entity) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var sprintModel = instantiateModel(entity);
        sprintModel.add(linkTo(methodOn(SprintController.class).getSprintById(entity.getId())).withSelfRel());
        sprintModel.setId(entity.getId());
        sprintModel.setName(entity.getName());
        sprintModel.setDescription(entity.getDescription());
        sprintModel.setStartDate(formatter.format(entity.getStartDate()));
        sprintModel.setEndDate(formatter.format(entity.getEndDate()));
        if (entity.getProject() != null) {
            sprintModel.setProject(lightProjectModelAssembler.toModel(entity.getProject()));
        }
        if (entity.getTasks() != null) {
            sprintModel.setTasks(taskModelAssembler.toCollectionModel(entity.getTasks()));
        }
        return sprintModel;
    }
}
