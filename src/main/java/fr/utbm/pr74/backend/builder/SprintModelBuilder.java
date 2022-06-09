package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.SprintModel;
import fr.utbm.pr74.backend.service.ProjectService;
import org.springframework.stereotype.Component;

@Component
public class SprintModelBuilder extends AbstractModelBuilder<Sprint, SprintModel> {
    private final ProjectService projectService;

    public SprintModelBuilder(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Sprint build(SprintModel model) {
        var sprint = new Sprint();
        sprint.setId(model.getId());
        sprint.setName(model.getName());
        sprint.setDescription(model.getDescription());
        sprint.setStartDate(model.getStartDate());
        sprint.setEndDate(model.getEndDate());
        sprint.setProject(projectService.getById(model.getProjectId()).orElseThrow());
        return sprint;
    }
}
