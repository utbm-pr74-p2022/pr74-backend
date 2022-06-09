package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Project extends AbstractEntity {
    private String name;
    @ManyToMany
    private List<User> users;
    @OneToMany
    private List<Priority> priorities;
    @OneToMany
    private List<Status> statuses;
    @OneToMany
    private List<Tag> tags;
}
