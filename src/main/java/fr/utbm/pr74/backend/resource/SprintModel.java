package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

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
    private Date startDate;
    private Date endDate;
    private Integer projectId;
    private List<TaskModel> tasks;
}
