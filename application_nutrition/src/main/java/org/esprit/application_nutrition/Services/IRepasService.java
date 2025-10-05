package org.esprit.application_nutrition.Services;

import org.esprit.application_nutrition.Entites.Repas;
import java.util.List;

public interface IRepasService {
    List<Repas> getAllRepas();
    Repas getRepasById(Long id);
    Repas createRepas(Repas repas);
    Repas updateRepas(Repas repas);
    void deleteRepas(Long id);
}
