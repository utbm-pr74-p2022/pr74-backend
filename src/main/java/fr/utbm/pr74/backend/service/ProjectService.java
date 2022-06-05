package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAll() {
        return repository.findAll();
    }

    public List<Project> getByUser(User user) {
        return repository.findByUsers(user);
    }

    public Project create(String name, List<User> users) {
        var project = new Project();
        project.setName(name);
        project.setUsers(users);
        return repository.save(project);
    }

    public Optional<Project> getById(Integer id) {
        return repository.findById(id);
    }
}
