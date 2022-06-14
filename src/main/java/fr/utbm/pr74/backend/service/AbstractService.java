package fr.utbm.pr74.backend.service;

import fr.utbm.pr74.backend.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, U extends JpaRepository<T, Integer>> {
    protected final U repository;


    protected AbstractService(U repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public Optional<T> getById(Integer id) {
        return repository.findById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T update(Integer id, T entity) {
        entity.setId(id);
        return repository.save(entity);
    }
}
