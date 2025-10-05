package org.esprit.application_nutrition.Services;

import org.esprit.application_nutrition.Entites.Ingredient;
import java.util.List;

public interface IIngredientService {
    List<Ingredient> getAllIngredients();
    Ingredient getIngredientById(Long id);
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient updateIngredient(Ingredient ingredient);
    void deleteIngredient(Long id);
}

