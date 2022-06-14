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
@Relation(collectionRelation = "projects")
public class ProjectModel extends RepresentationModel<LightProjectModel> {
    private Integer id;
    private String name;
    private CollectionModel<UserModel> users;
    private CollectionModel<PriorityModel> priorities;
    private CollectionModel<StatusModel> statuses;
    private BacklogModel backlog;
    private CollectionModel<LightSprintModel> sprints;
}
