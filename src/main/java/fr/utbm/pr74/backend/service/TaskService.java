package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Task;
import fr.utbm.pr74.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends AbstractService<Task, TaskRepository> {
    @Autowired
    public TaskService(TaskRepository repository) {
        super(repository);
    }

    @Override
    public Task update(Integer id, Task entity) {
        if (entity.getSprint() != null) {
            if (entity.getStatus() == null) {
                entity.setStatus(entity.getSprint().getProject().getStatuses().get(0));
            }
        } else if (entity.getBacklog() != null) {
            entity.setStatus(null);
        } else {
            throw new RuntimeException("Impossible to update task");
        }
        return super.update(id, entity);
    }
}
