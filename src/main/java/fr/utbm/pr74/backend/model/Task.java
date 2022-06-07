package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Task extends AbstractEntity {
    private String name;
    private String description;
    @ManyToOne
    private Priority priority;
    @ManyToOne
    private Status status;
    @ManyToMany
    private List<Tag> tags;
    @ManyToMany
    private List<Sprint> sprints;
    @OneToOne
    private Backlog backlog;
    @ManyToOne
    private User user;

}
