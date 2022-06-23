package fr.utbm.pr74.backend.resource;

import fr.utbm.pr74.backend.model.Role;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "users")
public class UserModel extends RepresentationModel<UserModel> {
    private Integer id;
    private String username;
    private String image;
    private Role role;
}
