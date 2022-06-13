package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "sprints")
public class SprintModel extends RepresentationModel<SprintModel> {
    private Integer id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private LightProjectModel project;
    private CollectionModel<TaskModel> tasks;
}
