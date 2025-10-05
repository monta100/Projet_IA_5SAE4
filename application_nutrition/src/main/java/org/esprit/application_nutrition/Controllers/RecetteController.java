package org.esprit.application_nutrition.Controllers;

import org.esprit.application_nutrition.Entites.Recette;
import org.esprit.application_nutrition.Services.IRecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recette")
public class RecetteController {

    @Autowired
    private IRecetteService recetteService;

    @GetMapping
    public List<Recette> getAllRecettes() {
        return recetteService.getAllRecettes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable Long id) {
        Recette recette = recetteService.getRecetteById(id);
        return (recette != null) ? new ResponseEntity<>(recette, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Recette createRecette(@RequestBody Recette recette) {
        return recetteService.createRecette(recette);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable Long id, @RequestBody Recette recetteDetails) {
        Recette existingRecette = recetteService.getRecetteById(id);
        if (existingRecette == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recetteDetails.setId(id);
        Recette updatedRecette = recetteService.updateRecette(recetteDetails);
        return new ResponseEntity<>(updatedRecette, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecette(@PathVariable Long id) {
        try {
            recetteService.deleteRecette(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

