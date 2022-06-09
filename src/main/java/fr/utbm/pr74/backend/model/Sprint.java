package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Sprint extends AbstractEntity {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Project project;
    @ManyToMany
    private List<Task> tasks;
}
