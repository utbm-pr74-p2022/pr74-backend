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
@Relation(collectionRelation = "tasks")
public class TaskModel extends RepresentationModel<TaskModel> {
    private Integer id;
    private String name;
    private String description;
    private PriorityModel priority;
    private StatusModel status;
    private LightSprintModel sprint;
    private LightBacklogModel backlog;
    private UserModel user;
}
