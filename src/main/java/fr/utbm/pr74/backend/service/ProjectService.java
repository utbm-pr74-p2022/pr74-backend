package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.model.Priority;
import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.model.Status;
import fr.utbm.pr74.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService extends AbstractService<Project, ProjectRepository> {
    private final PriorityService priorityService;
    private final StatusService statusService;
    private final BacklogService backlogService;
    private final SprintService sprintService;

    @Autowired
    public ProjectService(ProjectRepository repository, PriorityService priorityService, StatusService statusService, BacklogService backlogService, SprintService sprintService) {
        super(repository);
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.backlogService = backlogService;
        this.sprintService = sprintService;
    }

    @Override
    public Project create(Project entity) {
        var low = priorityService.create(new Priority("Low", "yellow-500"));
        var normal = priorityService.create(new Priority("Normal", "orange-500"));
        var high = priorityService.create(new Priority("High", "red-500"));
        if (entity.getPriorities() == null || entity.getPriorities().isEmpty()) {
            entity.setPriorities(List.of(low, normal, high));
        }
        var todo = statusService.create(new Status("To do"));
        var inProgress = statusService.create(new Status("In progress"));
        var done = statusService.create(new Status("Done"));
        if (entity.getStatuses() == null || entity.getStatuses().isEmpty()) {
            entity.setStatuses(List.of(todo, inProgress, done));
        }
        var backlog = backlogService.create(new Backlog());
        entity.setBacklog(backlog);
        entity.setDate(new Date());
        entity.setStatus("OPEN");
        return super.create(entity);
    }

    @Override
    public Project update(Integer id, Project entity) {
        var project = getById(id).orElseThrow();
        project.setName(entity.getName());
        project.setUsers(entity.getUsers());
        return super.update(id, project);
    }

    @Override
    public void delete(Integer id) {
        var project = getById(id).orElseThrow();
        Integer backlogId = null;
        if (project.getBacklog() != null) {
            backlogId = project.getBacklog().getId();
        }
        project.setBacklog(null);
        update(id, project);
        if (backlogId != null) {
            backlogService.delete(backlogId);
        }
        for (var sprint : project.getSprints()) {
            sprintService.delete(sprint.getId());
        }
        super.delete(id);
    }
}
