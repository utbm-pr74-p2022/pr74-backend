package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.repository.SprintRepository;
import org.springframework.stereotype.Service;

@Service
public class SprintService extends AbstractService<Sprint, SprintRepository> {
    private final TaskService taskService;
    protected SprintService(SprintRepository repository, TaskService taskService) {
        super(repository);
        this.taskService = taskService;
    }

    @Override
    public void delete(Integer id) {
        var sprint = getById(id).orElseThrow();
        for (var task : sprint.getTasks()) {
            taskService.delete(task.getId());
        }
        super.delete(id);
    }
}
