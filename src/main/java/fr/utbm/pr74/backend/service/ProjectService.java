package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<Project, ProjectRepository> {
    @Autowired
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
