package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "backlogs")
public class LightBacklogModel extends RepresentationModel<LightBacklogModel> {
    private Integer id;
    private LightProjectModel project;
}
