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
public class LightProjectModel extends RepresentationModel<LightProjectModel> {
    private Integer id;
    private String name;
    private String date;
    private String status;
    private CollectionModel<UserModel> users;
}
