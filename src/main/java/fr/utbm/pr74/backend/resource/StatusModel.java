package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "statuses")
public class StatusModel extends RepresentationModel<StatusModel> {
    private Integer id;
    private String name;
}
