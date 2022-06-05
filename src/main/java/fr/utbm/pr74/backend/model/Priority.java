package fr.utbm.pr74.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Priority {
    @Id
    private Integer id;
    private String name;
}
