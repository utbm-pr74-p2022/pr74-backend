package fr.utbm.pr74.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Status extends AbstractEntity {
    private String name;
}
