package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.repository.SprintRepository;
import org.springframework.stereotype.Service;

@Service
public class SprintService extends AbstractService<Sprint, SprintRepository> {
    protected SprintService(SprintRepository repository) {
        super(repository);
    }
}
