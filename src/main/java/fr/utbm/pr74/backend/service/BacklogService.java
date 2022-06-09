package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.repository.BacklogRepository;
import org.springframework.stereotype.Service;


@Service
public class BacklogService extends AbstractService<Backlog, BacklogRepository> {
    protected BacklogService(BacklogRepository repository) {
        super(repository);
    }
}
