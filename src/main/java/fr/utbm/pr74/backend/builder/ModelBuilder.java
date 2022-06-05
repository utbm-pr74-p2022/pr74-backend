package fr.utbm.pr74.backend.builder;

import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface ModelBuilder<T, U> {
    T build(U model);

    List<T> buildList(CollectionModel<U> models);
}
