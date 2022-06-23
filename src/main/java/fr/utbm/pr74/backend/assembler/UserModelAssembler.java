package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.ProjectController;
import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    public UserModelAssembler() {
        super(ProjectController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User entity) {
        var userModel = instantiateModel(entity);
        userModel.setId(entity.getId());
        userModel.setUsername(entity.getUsername());
        userModel.setImage(entity.getImage());
        userModel.setRole(entity.getRole());
        return userModel;
    }
}
