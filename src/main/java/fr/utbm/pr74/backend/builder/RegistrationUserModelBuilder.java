package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.RegistrationUserModel;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUserModelBuilder extends AbstractModelBuilder<User, RegistrationUserModel> {

    @Override
    public User build(RegistrationUserModel model) {
        var user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user.setImage(model.getImage());
        user.setRole(model.getRole());
        return user;
    }
}
