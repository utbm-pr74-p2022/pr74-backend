package fr.utbm.pr74.backend.repository;

import fr.utbm.pr74.backend.model.Project;
import fr.utbm.pr74.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUsers(User user);
}
