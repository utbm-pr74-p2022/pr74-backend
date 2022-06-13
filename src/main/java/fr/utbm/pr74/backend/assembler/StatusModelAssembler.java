package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.StatusController;
import fr.utbm.pr74.backend.model.Status;
import fr.utbm.pr74.backend.resource.StatusModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StatusModelAssembler extends RepresentationModelAssemblerSupport<Status, StatusModel> {
    public StatusModelAssembler() {
        super(StatusController.class, StatusModel.class);
    }

    @Override
    public StatusModel toModel(Status entity) {
        var statusModel = instantiateModel(entity);

        statusModel.add(linkTo(methodOn(StatusController.class).getStatusById(entity.getId())).withSelfRel());

        statusModel.setId(entity.getId());
        statusModel.setName(entity.getName());
        return statusModel;
    }
}
