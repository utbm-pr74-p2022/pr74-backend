package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.SprintController;
import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.LightSprintModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LightSprintModelAssembler extends RepresentationModelAssemblerSupport<Sprint, LightSprintModel> {
    public LightSprintModelAssembler() {
        super(SprintController.class, LightSprintModel.class);
    }

    @Override
    public LightSprintModel toModel(Sprint entity) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var lightSprintModel = instantiateModel(entity);

        lightSprintModel.add(linkTo(methodOn(SprintController.class).getSprintById(entity.getId())).withSelfRel());

        lightSprintModel.setId(entity.getId());
        lightSprintModel.setName(entity.getName());
        lightSprintModel.setStartDate(formatter.format(entity.getStartDate()));
        lightSprintModel.setEndDate(formatter.format(entity.getEndDate()));
        return lightSprintModel;
    }
}
