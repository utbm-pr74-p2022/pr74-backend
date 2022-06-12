package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.StatusModelAssembler;
import fr.utbm.pr74.backend.resource.StatusModel;
import fr.utbm.pr74.backend.service.StatusService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class StatusController {
    private final StatusService statusService;
    private final StatusModelAssembler statusModelAssembler;

    public StatusController(StatusService statusService, StatusModelAssembler statusModelAssembler) {
        this.statusService = statusService;
        this.statusModelAssembler = statusModelAssembler;
    }

    @GetMapping("/status")
    public ResponseEntity<CollectionModel<StatusModel>> getAll() {
        return new ResponseEntity<>(statusModelAssembler.toCollectionModel(statusService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<StatusModel> getStatusById(@PathVariable Integer id) {
        return statusService.getById(id)
                .map(statusModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
