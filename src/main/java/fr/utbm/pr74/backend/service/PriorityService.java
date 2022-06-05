package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository repository;

    @Autowired
    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }
}
