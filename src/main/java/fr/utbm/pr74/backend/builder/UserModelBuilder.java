package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelBuilder extends AbstractModelBuilder<User, UserModel> {
    private final UserService userService;

    @Autowired
    public UserModelBuilder(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User build(UserModel model) {
        return userService.get(model.getId()).orElseThrow();
    }
}
