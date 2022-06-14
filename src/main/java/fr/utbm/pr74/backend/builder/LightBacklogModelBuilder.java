package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Backlog;
import fr.utbm.pr74.backend.resource.LightBacklogModel;
import org.springframework.stereotype.Component;

@Component
public class LightBacklogModelBuilder extends AbstractModelBuilder<Backlog, LightBacklogModel> {

    @Override
    public Backlog build(LightBacklogModel model) {
        Backlog backlog = new Backlog();
        backlog.setId(model.getId());
        return backlog;
    }
}
