package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.UserModelAssembler;
import fr.utbm.pr74.backend.builder.RegistrationUserModelBuilder;
import fr.utbm.pr74.backend.resource.RegistrationUserModel;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final RegistrationUserModelBuilder registrationUserModelBuilder;


    @Autowired
    public UserController(UserService userService, UserModelAssembler userModelAssembler, RegistrationUserModelBuilder registrationUserModelBuilder) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
        this.registrationUserModelBuilder = registrationUserModelBuilder;
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody RegistrationUserModel registrationUserModel) {
        var user = registrationUserModelBuilder.build(registrationUserModel);
        return new ResponseEntity<>(userModelAssembler.toModel(userService.create(user)), HttpStatus.CREATED);
    }
}
