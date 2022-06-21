package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.repository.BacklogRepository;
import org.springframework.stereotype.Service;


@Service
public class BacklogService extends AbstractService<Backlog, BacklogRepository> {
    private final TaskService taskService;

    protected BacklogService(BacklogRepository repository, TaskService taskService) {
        super(repository);
        this.taskService = taskService;
    }

    @Override
    public void delete(Integer id) {
        var backlog = getById(id).orElseThrow();
        for (var task : backlog.getTasks()) {
            taskService.delete(task.getId());
        }
        super.delete(id);
    }
}
