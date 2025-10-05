package org.esprit.application_nutrition.Repo;

import org.esprit.application_nutrition.Entites.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

