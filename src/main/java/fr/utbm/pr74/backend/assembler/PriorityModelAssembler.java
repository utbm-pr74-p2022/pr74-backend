package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.PriorityController;
import fr.utbm.pr74.backend.model.Priority;
import fr.utbm.pr74.backend.resource.PriorityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PriorityModelAssembler extends RepresentationModelAssemblerSupport<Priority, PriorityModel> {
    public PriorityModelAssembler() {
        super(PriorityController.class, PriorityModel.class);
    }

    @Override
    public PriorityModel toModel(Priority entity) {
        var priorityModel = instantiateModel(entity);

        priorityModel.add(linkTo(methodOn(PriorityController.class).getPriorityById(entity.getId())).withSelfRel());

        priorityModel.setId(entity.getId());
        priorityModel.setName(entity.getName());
        return priorityModel;
    }
}
