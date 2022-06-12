package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.PriorityModelAssembler;
import fr.utbm.pr74.backend.resource.PriorityModel;
import fr.utbm.pr74.backend.service.PriorityService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class PriorityController {
    private final PriorityService priorityService;
    private final PriorityModelAssembler priorityModelAssembler;

    public PriorityController(PriorityService priorityService, PriorityModelAssembler priorityModelAssembler) {
        this.priorityService = priorityService;
        this.priorityModelAssembler = priorityModelAssembler;
    }

    @GetMapping("/priority")
    public ResponseEntity<CollectionModel<PriorityModel>> getAll() {
        return new ResponseEntity<>(priorityModelAssembler.toCollectionModel(priorityService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/priority/{id}")
    public ResponseEntity<PriorityModel> getPriorityById(@PathVariable Integer id) {
        return priorityService.getById(id)
                .map(priorityModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
