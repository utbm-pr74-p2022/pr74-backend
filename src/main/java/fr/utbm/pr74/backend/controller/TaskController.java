package fr.utbm.pr74.backend.controller;

import fr.utbm.pr74.backend.assembler.TaskModelAssembler;
import fr.utbm.pr74.backend.builder.TaskModelBuilder;
import fr.utbm.pr74.backend.resource.TaskModel;
import fr.utbm.pr74.backend.service.TaskService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class TaskController {
    private final TaskService taskService;
    private final TaskModelAssembler taskModelAssembler;
    private final TaskModelBuilder taskModelBuilder;

    public TaskController(TaskService taskService, TaskModelAssembler taskModelAssembler, TaskModelBuilder taskModelBuilder) {
        this.taskService = taskService;
        this.taskModelAssembler = taskModelAssembler;
        this.taskModelBuilder = taskModelBuilder;
    }

    @GetMapping("/task")
    public ResponseEntity<CollectionModel<TaskModel>> getAll() {
        return new ResponseEntity<>(taskModelAssembler.toCollectionModel(taskService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable Integer id) {
        return taskService.getById(id)
                .map(taskModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel) {
        var task = taskModelBuilder.build(taskModel);
        return new ResponseEntity<>(taskModelAssembler.toModel(taskService.create(task)), HttpStatus.CREATED);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable Integer id, @RequestBody TaskModel taskModel) {
        var task = taskModelBuilder.build(taskModel);
        return new ResponseEntity<>(taskModelAssembler.toModel(taskService.update(id, task)), HttpStatus.OK);
    }

}
