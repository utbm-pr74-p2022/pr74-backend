package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.LightSprintModel;
import fr.utbm.pr74.backend.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightSprintModelBuilder extends AbstractModelBuilder<Sprint, LightSprintModel> {
    private final SprintService sprintService;

    @Autowired
    public LightSprintModelBuilder(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public Sprint build(LightSprintModel model) {
        return sprintService.getById(model.getId()).orElseThrow();
    }
}
