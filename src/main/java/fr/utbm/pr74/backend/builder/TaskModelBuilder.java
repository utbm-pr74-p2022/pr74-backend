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
    private final LightBacklogModelBuilder lightBacklogModelBuilder;

    public TaskModelBuilder(PriorityService priorityService, StatusService statusService, LightSprintModelBuilder lightSprintModelBuilder, UserModelBuilder userModelBuilder, LightBacklogModelBuilder lightBacklogModelBuilder) {
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.lightSprintModelBuilder = lightSprintModelBuilder;
        this.userModelBuilder = userModelBuilder;
        this.lightBacklogModelBuilder = lightBacklogModelBuilder;
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
        if (model.getSprint() != null && model.getSprint().getId() != null) {
            task.setSprint(lightSprintModelBuilder.build(model.getSprint()));
        }
        if (model.getBacklog() != null && model.getBacklog().getId() != null) {
            task.setBacklog(lightBacklogModelBuilder.build(model.getBacklog()));
        }
        if (model.getUser() != null) {
            task.setUser(userModelBuilder.build(model.getUser()));
        }
        return task;
    }
}
