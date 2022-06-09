package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.LoginUserModelAssembler;
import fr.utbm.pr74.backend.assembler.UserModelAssembler;
import fr.utbm.pr74.backend.builder.RegistrationUserModelBuilder;
import fr.utbm.pr74.backend.configuration.jwt.JwtProvider;
import fr.utbm.pr74.backend.resource.LoginUserModel;
import fr.utbm.pr74.backend.resource.RegistrationUserModel;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final LoginUserModelAssembler loginUserModelAssembler;
    private final RegistrationUserModelBuilder registrationUserModelBuilder;


    @Autowired
    public UserController(UserService userService, UserModelAssembler userModelAssembler, LoginUserModelAssembler loginUserModelAssembler, RegistrationUserModelBuilder registrationUserModelBuilder, AuthenticationManager authenticationManager, JwtProvider tokenProvider) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
        this.loginUserModelAssembler = loginUserModelAssembler;
        this.registrationUserModelBuilder = registrationUserModelBuilder;
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody RegistrationUserModel registrationUserModel) {
        var user = registrationUserModelBuilder.build(registrationUserModel);
        return userService.register(user)
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserModel> login(@RequestBody RegistrationUserModel registrationUserModel) {
        var user = registrationUserModelBuilder.build(registrationUserModel);
        return userService.login(user)
                .map(loginUserModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
