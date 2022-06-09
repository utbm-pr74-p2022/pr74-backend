package fr.utbm.pr74.backend.builder;

import org.springframework.hateoas.CollectionModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModelBuilder<T, U> implements ModelBuilder<T, U> {
    @Override
    public List<T> buildList(CollectionModel<U> models) {
        List<T> entities = new ArrayList<>();
        if (models != null) {
            models.getContent().forEach(model -> entities.add(build(model)));
        }
        return entities;
    }
}
