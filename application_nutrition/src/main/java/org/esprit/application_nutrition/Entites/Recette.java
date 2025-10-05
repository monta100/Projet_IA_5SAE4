package org.esprit.application_nutrition.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Recette implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double calories;

    @ManyToOne
    @JoinColumn(name="repas_id")
    private Repas repas;

    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;
}
