package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.BacklogModelAssembler;
import fr.utbm.pr74.backend.resource.BacklogModel;
import fr.utbm.pr74.backend.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class BacklogController {
    private final BacklogService backlogService;
    private final BacklogModelAssembler backlogModelAssembler;

    @Autowired
    public BacklogController(BacklogService backlogService, BacklogModelAssembler backlogModelAssembler) {
        this.backlogService = backlogService;
        this.backlogModelAssembler = backlogModelAssembler;
    }

    @GetMapping("/backlog")
    public ResponseEntity<CollectionModel<BacklogModel>> getAll() {
        return new ResponseEntity<>(backlogModelAssembler.toCollectionModel(backlogService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/backlog/{id}")
    public ResponseEntity<BacklogModel> getBacklogById(@PathVariable Integer id) {
        return backlogService.getById(id)
                .map(backlogModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
