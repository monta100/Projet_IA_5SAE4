package org.esprit.application_nutrition.Entites;



import jakarta.persistence.*;
        import lombok.*;

        import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data                   // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Constructeur sans argument
@AllArgsConstructor     // Constructeur avec tous les arguments
@Builder                // Permet d'utiliser le pattern Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    @Enumerated(EnumType.STRING)   // Sauvegarde la valeur sous forme de texte ("ADHERENT", "COACH", "ADMIN")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Repas> repas;
    /*
    // Un utilisateur peut avoir plusieurs objectifs
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Objectif> objectifs;


 */
}
