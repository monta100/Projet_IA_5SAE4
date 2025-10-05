package org.esprit.application_nutrition.Services;

import org.esprit.application_nutrition.Entites.Repas;
import org.esprit.application_nutrition.Repo.RepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepasServiceImpl implements IRepasService {

    @Autowired
    private RepasRepository repasRepository;

    @Override
    public List<Repas> getAllRepas() {
        return repasRepository.findAll();
    }

    @Override
    public Repas getRepasById(Long id) {
        return repasRepository.findById(id).orElse(null);
    }

    @Override
    public Repas createRepas(Repas repas) {
        return repasRepository.save(repas);
    }

    @Override
    public Repas updateRepas(Repas repas) {
        return repasRepository.save(repas);
    }

    @Override
    public void deleteRepas(Long id) {
        repasRepository.deleteById(id);
    }
}
