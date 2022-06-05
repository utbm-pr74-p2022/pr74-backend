package fr.utbm.pr74.backend.resource;

import fr.utbm.pr74.backend.model.Priority;
import lombok.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "projects")
public class ProjectModel extends RepresentationModel<LightProjectModel> {
    private Integer id;
    private String name;
    private CollectionModel<UserModel> users;
    private CollectionModel<Priority> priorities;
}
