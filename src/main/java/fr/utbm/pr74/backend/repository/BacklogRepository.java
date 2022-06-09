package fr.utbm.pr74.backend.repository;

import fr.utbm.pr74.backend.model.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer> {
}
