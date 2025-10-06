package org.esprit.application_nutrition.Entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Repas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeRepas type;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Double caloriesTotales;
    @ManyToOne
    @JoinColumn(name="user_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private User user;
    @OneToMany(mappedBy = "repas", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<Recette> recettes;
}
