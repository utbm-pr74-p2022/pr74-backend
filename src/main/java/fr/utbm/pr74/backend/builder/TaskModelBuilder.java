package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Task;
import fr.utbm.pr74.backend.resource.TaskModel;
import fr.utbm.pr74.backend.service.PriorityService;
import fr.utbm.pr74.backend.service.StatusService;
import org.springframework.stereotype.Component;

@Component
public class TaskModelBuilder extends AbstractModelBuilder<Task, TaskModel> {
    private final PriorityService priorityService;
    private final StatusService statusService;
    private final LightSprintModelBuilder lightSprintModelBuilder;
    private final UserModelBuilder userModelBuilder;

    public TaskModelBuilder(PriorityService priorityService, StatusService statusService, LightSprintModelBuilder lightSprintModelBuilder, UserModelBuilder userModelBuilder) {
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.lightSprintModelBuilder = lightSprintModelBuilder;
        this.userModelBuilder = userModelBuilder;
    }

    @Override
    public Task build(TaskModel model) {
        var task = new Task();
        task.setId(model.getId());
        task.setName(model.getName());
        task.setDescription(model.getDescription());
        if (model.getPriority() != null && model.getPriority().getId() != null) {
            task.setPriority(priorityService.getById(model.getPriority().getId()).orElseThrow());
        }
        if (model.getStatus() != null && model.getStatus().getId() != null) {
            task.setStatus(statusService.getById(model.getStatus().getId()).orElseThrow());
        }
        task.setSprints(lightSprintModelBuilder.buildList(model.getSprints()));
        if (model.getUser() != null) {
            task.setUser(userModelBuilder.build(model.getUser()));
        }
        return task;
    }
}
