package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    @OneToOne
    private Backlog backlog;
    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints;
    private Date date;
    private String status;
}
