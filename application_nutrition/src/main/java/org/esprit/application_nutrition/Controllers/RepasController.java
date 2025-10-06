package org.esprit.application_nutrition.Controllers;

import org.esprit.application_nutrition.Entites.Repas;
import org.esprit.application_nutrition.Services.IRepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repas")
public class RepasController {

    @Autowired
    private IRepasService repasService;

    @GetMapping
    public List<Repas> getAllRepas() {
        return repasService.getAllRepas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repas> getRepasById(@PathVariable Long id) {
        Repas repas = repasService.getRepasById(id);
        return (repas != null) ? new ResponseEntity<>(repas, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Repas createRepas(@RequestBody Repas repas) {
        return repasService.createRepas(repas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repas> updateRepas(@PathVariable Long id, @RequestBody Repas repasDetails) {
        Repas existingRepas = repasService.getRepasById(id);
        if (existingRepas == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Repas updatedRepas = repasService.updateRepas(repasDetails);
        return new ResponseEntity<>(updatedRepas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRepas(@PathVariable Long id) {
        try {
            repasService.deleteRepas(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
