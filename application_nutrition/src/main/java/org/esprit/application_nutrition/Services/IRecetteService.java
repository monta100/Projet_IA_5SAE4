package org.esprit.application_nutrition.Services;

import org.esprit.application_nutrition.Entites.Recette;
import java.util.List;

public interface IRecetteService {
    List<Recette> getAllRecettes();
    Recette getRecetteById(Long id);
    Recette createRecette(Recette recette);
    Recette updateRecette(Recette recette);
    void deleteRecette(Long id);
}

