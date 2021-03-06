package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.LoginUserModelAssembler;
import fr.utbm.pr74.backend.assembler.UserModelAssembler;
import fr.utbm.pr74.backend.builder.RegistrationUserModelBuilder;
import fr.utbm.pr74.backend.builder.UserModelBuilder;
import fr.utbm.pr74.backend.configuration.jwt.JwtProvider;
import fr.utbm.pr74.backend.resource.LoginUserModel;
import fr.utbm.pr74.backend.resource.RegistrationUserModel;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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
    private final UserModelBuilder userModelBuilder;


    @Autowired
    public UserController(UserService userService, UserModelAssembler userModelAssembler, LoginUserModelAssembler loginUserModelAssembler, RegistrationUserModelBuilder registrationUserModelBuilder, AuthenticationManager authenticationManager, JwtProvider tokenProvider, UserModelBuilder userModelBuilder) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
        this.loginUserModelAssembler = loginUserModelAssembler;
        this.registrationUserModelBuilder = registrationUserModelBuilder;
        this.userModelBuilder = userModelBuilder;
    }

    @GetMapping("/user")
    public ResponseEntity<CollectionModel<UserModel>> getAll() {
        return new ResponseEntity<>(userModelAssembler.toCollectionModel(userService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody RegistrationUserModel registrationUserModel) {
        var user = registrationUserModelBuilder.build(registrationUserModel);
        return userService.register(user)
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel userModel) {
        var user = userModelBuilder.build(userModel);
        return new ResponseEntity<>(userModelAssembler.toModel(userService.update(id, user)), HttpStatus.OK);
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
