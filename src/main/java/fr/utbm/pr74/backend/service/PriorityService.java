package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Priority;
import fr.utbm.pr74.backend.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService extends AbstractService<Priority, PriorityRepository> {
    @Autowired
    public PriorityService(PriorityRepository repository) {
        super(repository);
    }
}
