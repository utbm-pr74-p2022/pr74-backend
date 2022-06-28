package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserModelBuilder extends AbstractModelBuilder<User, UserModel> {
    @Override
    public User build(UserModel model) {
        var user = new User();
        if (model == null) {
            return user;
        }
        user.setId(model.getId());
        user.setUsername(model.getUsername());
        user.setImage(model.getImage());
        user.setRole(model.getRole());
        user.setEnabled(model.isEnabled());
        return user;
    }
}
