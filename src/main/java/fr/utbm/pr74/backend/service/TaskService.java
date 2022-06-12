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
}
