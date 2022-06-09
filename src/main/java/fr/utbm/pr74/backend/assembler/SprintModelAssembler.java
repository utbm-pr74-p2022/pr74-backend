package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.SprintController;
import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.SprintModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SprintModelAssembler extends RepresentationModelAssemblerSupport<Sprint, SprintModel> {
    public SprintModelAssembler() {
        super(SprintController.class, SprintModel.class);
    }

    @Override
    public SprintModel toModel(Sprint entity) {
        var sprintModel = instantiateModel(entity);
        sprintModel.add(linkTo(methodOn(SprintController.class).getSprintById(entity.getId())).withSelfRel());
        sprintModel.setId(entity.getId());
        sprintModel.setName(entity.getName());
        sprintModel.setDescription(entity.getDescription());
        sprintModel.setStartDate(entity.getStartDate());
        sprintModel.setEndDate(entity.getEndDate());
        sprintModel.setProjectId(entity.getProject().getId());
        return sprintModel;
    }
}
