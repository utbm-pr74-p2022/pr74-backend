package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Status;
import fr.utbm.pr74.backend.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends AbstractService<Status, StatusRepository> {
    public StatusService(StatusRepository repository) {
        super(repository);
    }
}
