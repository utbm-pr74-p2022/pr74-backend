package fr.utbm.pr74.backend.resource;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "priorities")
public class PriorityModel extends RepresentationModel<PriorityModel> {
    private Integer id;
    private String name;
    private String color;
}
