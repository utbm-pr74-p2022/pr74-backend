package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.UserModelAssembler;
import fr.utbm.pr74.backend.builder.UserModelBuilder;
import fr.utbm.pr74.backend.resource.UserModel;
import fr.utbm.pr74.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final UserModelBuilder userModelBuilder;


    public UserController(UserService userService, UserModelAssembler userModelAssembler, UserModelBuilder userModelBuilder) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
        this.userModelBuilder = userModelBuilder;
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        var user = userModelBuilder.build(userModel);
        return new ResponseEntity<>(userModelAssembler.toModel(userService.create(user)), HttpStatus.CREATED);
    }
}
