package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.Role;
import fr.utbm.pr74.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role, RoleRepository> {
    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
