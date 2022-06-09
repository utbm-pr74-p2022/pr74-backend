package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.SprintModelAssembler;
import fr.utbm.pr74.backend.builder.SprintModelBuilder;
import fr.utbm.pr74.backend.resource.SprintModel;
import fr.utbm.pr74.backend.service.SprintService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class SprintController {
    private final SprintService sprintService;
    private final SprintModelAssembler sprintModelAssembler;
    private final SprintModelBuilder sprintModelBuilder;

    public SprintController(SprintService sprintService, SprintModelAssembler sprintModelAssembler, SprintModelBuilder sprintModelBuilder) {
        this.sprintService = sprintService;
        this.sprintModelAssembler = sprintModelAssembler;
        this.sprintModelBuilder = sprintModelBuilder;
    }

    @GetMapping("/sprint")
    public ResponseEntity<CollectionModel<SprintModel>> getAll() {
        return new ResponseEntity<>(sprintModelAssembler.toCollectionModel(sprintService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/sprint/{id}")
    public ResponseEntity<SprintModel> getSprintById(@PathVariable Integer id) {
        return sprintService.getById(id)
                .map(sprintModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/sprint")
    public ResponseEntity<SprintModel> createSprint(@RequestBody SprintModel sprintModel) {
        var sprint = sprintModelBuilder.build(sprintModel);
        return new ResponseEntity<>(sprintModelAssembler.toModel(sprintService.create(sprint)), HttpStatus.CREATED);
    }
}
