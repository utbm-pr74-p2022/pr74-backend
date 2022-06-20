package fr.utbm.pr74.backend.assembler;


import fr.utbm.pr74.backend.controller.UserController;
import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.LoginUserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class LoginUserModelAssembler extends RepresentationModelAssemblerSupport<User, LoginUserModel> {
    public LoginUserModelAssembler() {
        super(UserController.class, LoginUserModel.class);
    }

    @Override
    public LoginUserModel toModel(User entity) {
        var userModel = instantiateModel(entity);
        userModel.setId(entity.getId());
        userModel.setUsername(entity.getUsername());
        userModel.setToken(entity.getToken());
        userModel.setRole(entity.getRole().getAuthority());
        return userModel;
    }
}
