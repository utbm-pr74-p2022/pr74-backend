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
    public Task create(Task entity) {
        prepareTask(entity);
        return super.create(entity);
    }

    @Override
    public Task update(Integer id, Task entity) {
        prepareTask(entity);
        return super.update(id, entity);
    }

    private void prepareTask(Task task) {
        if (task.getSprint() != null) {
            if (task.getStatus() == null) {
                task.setStatus(task.getSprint().getProject().getStatuses().get(0));
            }
        } else if (task.getBacklog() != null) {
            task.setStatus(null);
        } else {
            throw new RuntimeException("Impossible to update task");
        }
    }
}
