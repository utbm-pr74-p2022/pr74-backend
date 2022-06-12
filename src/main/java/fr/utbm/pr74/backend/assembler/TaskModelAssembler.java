package fr.utbm.pr74.backend.assembler;

import fr.utbm.pr74.backend.controller.TaskController;
import fr.utbm.pr74.backend.model.Task;
import fr.utbm.pr74.backend.resource.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskModel> {
    private final PriorityModelAssembler priorityModelAssembler;
    private final StatusModelAssembler statusModelAssembler;
    private final LightSprintModelAssembler lightSprintModelAssembler;
    private final UserModelAssembler userModelAssembler;

    @Autowired
    public TaskModelAssembler(PriorityModelAssembler priorityModelAssembler, StatusModelAssembler statusModelAssembler, LightSprintModelAssembler lightSprintModelAssembler, UserModelAssembler userModelAssembler) {
        super(TaskController.class, TaskModel.class);
        this.priorityModelAssembler = priorityModelAssembler;
        this.statusModelAssembler = statusModelAssembler;
        this.lightSprintModelAssembler = lightSprintModelAssembler;
        this.userModelAssembler = userModelAssembler;
    }

    @Override
    public TaskModel toModel(Task entity) {
        var taskModel = instantiateModel(entity);
        taskModel.setId(entity.getId());
        taskModel.setName(entity.getName());
        taskModel.setDescription(entity.getDescription());
        if (entity.getPriority() != null) {
            taskModel.setPriority(priorityModelAssembler.toModel(entity.getPriority()));
        }
        if (entity.getStatus() != null) {
            taskModel.setStatus(statusModelAssembler.toModel(entity.getStatus()));
        }
        if (entity.getSprints() != null) {
            taskModel.setSprints(lightSprintModelAssembler.toCollectionModel(entity.getSprints()));
        }
        if (entity.getUser() != null) {
            taskModel.setUser(userModelAssembler.toModel(entity.getUser()));
        }
        return taskModel;
    }
}
