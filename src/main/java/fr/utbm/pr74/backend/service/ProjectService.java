package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Priority;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.model.Status;
import fr.utbm.pr74.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService extends AbstractService<Project, ProjectRepository> {
    private final PriorityService priorityService;
    private final StatusService statusService;

    @Autowired
    public ProjectService(ProjectRepository repository, PriorityService priorityService, StatusService statusService) {
        super(repository);
        this.priorityService = priorityService;
        this.statusService = statusService;
    }

    @Override
    public Project create(Project entity) {
        var low = priorityService.create(new Priority("Low"));
        var normal = priorityService.create(new Priority("Normal"));
        var high = priorityService.create(new Priority("High"));
        if (entity.getPriorities() == null) {
            entity.setPriorities(List.of(low, normal, high));
        }
        var todo = statusService.create(new Status("To do"));
        var inProgress = statusService.create(new Status("In progress"));
        var done = statusService.create(new Status("Done"));
        if (entity.getStatuses() == null) {
            entity.setStatuses(List.of(todo, inProgress, done));
        }
        return super.create(entity);
    }
}
