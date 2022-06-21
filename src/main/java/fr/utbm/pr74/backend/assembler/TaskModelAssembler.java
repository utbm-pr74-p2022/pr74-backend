package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.TaskController;
import fr.utbm.pr74.backend.model.Task;
import fr.utbm.pr74.backend.resource.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskModel> {
    private final PriorityModelAssembler priorityModelAssembler;
    private final StatusModelAssembler statusModelAssembler;
    private final LightSprintModelAssembler lightSprintModelAssembler;
    private final UserModelAssembler userModelAssembler;
    private final LightBacklogModelAssembler lightBacklogModelAssembler;

    @Autowired
    public TaskModelAssembler(PriorityModelAssembler priorityModelAssembler, StatusModelAssembler statusModelAssembler, LightSprintModelAssembler lightSprintModelAssembler, UserModelAssembler userModelAssembler, LightBacklogModelAssembler lightBacklogModelAssembler) {
        super(TaskController.class, TaskModel.class);
        this.priorityModelAssembler = priorityModelAssembler;
        this.statusModelAssembler = statusModelAssembler;
        this.lightSprintModelAssembler = lightSprintModelAssembler;
        this.userModelAssembler = userModelAssembler;
        this.lightBacklogModelAssembler = lightBacklogModelAssembler;
    }

    @Override
    public TaskModel toModel(Task entity) {
        var taskModel = instantiateModel(entity);

        taskModel.add(linkTo(methodOn(TaskController.class).getTaskById(entity.getId())).withSelfRel());

        taskModel.setId(entity.getId());
        taskModel.setName(entity.getName());
        taskModel.setDescription(entity.getDescription());
        if (entity.getPriority() != null) {
            taskModel.setPriority(priorityModelAssembler.toModel(entity.getPriority()));
        }
        if (entity.getStatus() != null) {
            taskModel.setStatus(statusModelAssembler.toModel(entity.getStatus()));
        }
        if (entity.getSprint() != null) {
            taskModel.setSprint(lightSprintModelAssembler.toModel(entity.getSprint()));
        }
        if (entity.getBacklog() != null) {
            taskModel.setBacklog(lightBacklogModelAssembler.toModel(entity.getBacklog()));
        }
        if (entity.getUser() != null) {
            taskModel.setUser(userModelAssembler.toModel(entity.getUser()));
        }
        return taskModel;
    }
}
