package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.configuration.jwt.JwtProvider;
import fr.utbm.pr74.backend.model.User;
import fr.utbm.pr74.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider tokenProvider) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public Optional<User> register(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            return Optional.empty();
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(create(user));
    }

    public Optional<User> login(User user) {
        return repository.findByUsername(user.getUsername()).map(u -> {
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                var authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                u.setToken(tokenProvider.generateToken(authentication));
                return u;
            }
            return null;
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
