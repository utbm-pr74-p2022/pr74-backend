package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> register(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            return Optional.empty();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(create(user));
    }

    public Optional<User> login(User user) {
        return repository.findByUsername(user.getUsername()).map(u -> {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                return u;
            }
            return null;
        });
    }
}
