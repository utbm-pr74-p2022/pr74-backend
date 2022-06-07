package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
public class Backlog extends AbstractEntity {
    @OneToOne
    private Project project;
    @OneToMany
    private List<Task> tasks;
}
