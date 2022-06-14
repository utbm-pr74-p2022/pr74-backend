package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "backlogs")
public class BacklogModel extends RepresentationModel<BacklogModel> {
    private Integer id;
    private LightProjectModel project;
    private CollectionModel<TaskModel> tasks;
}
