package org.esprit.application_nutrition.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double quantite;
    private String unite;
    private Double calories;

    @ManyToOne
    @JoinColumn(name="recette_id")
    private Recette recette;
}

