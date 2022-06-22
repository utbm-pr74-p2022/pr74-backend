package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Priority;
import fr.utbm.pr74.backend.resource.PriorityModel;
import org.springframework.stereotype.Component;

@Component
public class PriorityModelBuilder extends AbstractModelBuilder<Priority, PriorityModel> {
    @Override
    public Priority build(PriorityModel model) {
        var priority = new Priority();
        priority.setId(model.getId());
        priority.setName(model.getName());
        priority.setColor(model.getColor());
        return priority;
    }
}
