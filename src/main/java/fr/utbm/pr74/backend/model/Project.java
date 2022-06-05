package fr.utbm.pr74.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany
    private List<User> users;
    @OneToMany
    private List<Priority> priorities;
}
