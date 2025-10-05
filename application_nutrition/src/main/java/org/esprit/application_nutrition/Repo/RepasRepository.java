package org.esprit.application_nutrition.Repo;

import org.esprit.application_nutrition.Entites.Repas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasRepository extends JpaRepository<Repas, Long> {
}
