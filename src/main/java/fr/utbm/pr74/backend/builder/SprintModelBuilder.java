package fr.utbm.pr74.backend.builder;

import fr.utbm.pr74.backend.model.Sprint;
import fr.utbm.pr74.backend.resource.SprintModel;
import fr.utbm.pr74.backend.service.ProjectService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class SprintModelBuilder extends AbstractModelBuilder<Sprint, SprintModel> {
    private final ProjectService projectService;

    public SprintModelBuilder(ProjectService projectService) {
        this.projectService = projectService;
    }

    @SneakyThrows
    @Override
    public Sprint build(SprintModel model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        var sprint = new Sprint();
        sprint.setId(model.getId());
        sprint.setName(model.getName());
        sprint.setDescription(model.getDescription());
        sprint.setStartDate(formatter.parse(model.getStartDate()));
        sprint.setEndDate(formatter.parse(model.getEndDate()));
        sprint.setProject(projectService.getById(model.getProject().getId()).orElseThrow());
        return sprint;
    }
}
