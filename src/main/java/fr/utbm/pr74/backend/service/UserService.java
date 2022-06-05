package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> get(Integer id) {
        return repository.findById(id);
    }

    public User create(User user) {
        return repository.save(user);
    }
}
