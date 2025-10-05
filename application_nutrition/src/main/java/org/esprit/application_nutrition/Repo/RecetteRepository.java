package org.esprit.application_nutrition.Repo;

import org.esprit.application_nutrition.Entites.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {
}

