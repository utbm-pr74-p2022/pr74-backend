package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Status;
import fr.utbm.pr74.backend.resource.StatusModel;
import org.springframework.stereotype.Component;

@Component
public class StatusModelBuilder extends AbstractModelBuilder<Status, StatusModel> {
    @Override
    public Status build(StatusModel model) {
        var status = new Status();
        status.setId(model.getId());
        status.setName(model.getName());
        return status;
    }
}
