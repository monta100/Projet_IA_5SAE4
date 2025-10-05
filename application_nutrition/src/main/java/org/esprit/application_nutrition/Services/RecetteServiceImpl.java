package org.esprit.application_nutrition.Services;

import org.esprit.application_nutrition.Entites.Recette;
import org.esprit.application_nutrition.Repo.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetteServiceImpl implements IRecetteService {

    @Autowired
    private RecetteRepository recetteRepository;

    @Override
    public List<Recette> getAllRecettes() {
        return recetteRepository.findAll();
    }

    @Override
    public Recette getRecetteById(Long id) {
        return recetteRepository.findById(id).orElse(null);
    }

    @Override
    public Recette createRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    @Override
    public Recette updateRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    @Override
    public void deleteRecette(Long id) {
        recetteRepository.deleteById(id);
    }
}
