package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.LightProjectModelAssembler;
import fr.utbm.pr74.backend.assembler.ProjectModelAssembler;
import fr.utbm.pr74.backend.resource.LightProjectModel;
import fr.utbm.pr74.backend.resource.ProjectModel;
import fr.utbm.pr74.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final LightProjectModelAssembler lightProjectModelAssembler;
    private final ProjectModelAssembler projectModelAssembler;

    @Autowired
    public ProjectController(ProjectService projectService, LightProjectModelAssembler projectModelAssembler, ProjectModelAssembler projectModelAssembler1) {
        this.projectService = projectService;
        this.lightProjectModelAssembler = projectModelAssembler;
        this.projectModelAssembler = projectModelAssembler1;
    }

    @GetMapping("/project")
    public ResponseEntity<CollectionModel<LightProjectModel>> getAll() {
        return new ResponseEntity<>(lightProjectModelAssembler.toCollectionModel(projectService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectModel> getProjectById(@PathVariable("id") Integer id) {
        return projectService.getById(id)
                .map(projectModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
