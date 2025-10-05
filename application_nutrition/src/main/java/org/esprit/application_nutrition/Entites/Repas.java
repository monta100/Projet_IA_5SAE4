package org.esprit.application_nutrition.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Repas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeRepas type;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Double caloriesTotales;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "repas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recette> recettes;
}
