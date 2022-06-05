package fr.utbm.pr74.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<Priority> priorities;
}
