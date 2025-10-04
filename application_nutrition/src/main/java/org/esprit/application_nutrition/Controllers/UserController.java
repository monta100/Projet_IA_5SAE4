package org.esprit.application_nutrition.Controllers;

import lombok.RequiredArgsConstructor;
import org.esprit.application_nutrition.Entites.User;
import org.esprit.application_nutrition.Services.ServiceUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final ServiceUser serviceUser;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            return ResponseEntity.ok(serviceUser.inscrire(user));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User u = serviceUser.seConnecter(user.getEmail(), user.getMotDePasse());
        if(u != null) return ResponseEntity.ok(u);
        return ResponseEntity.status(401).body("Email ou mot de passe incorrect !");
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam String token) {
        return ResponseEntity.ok(serviceUser.confirmerToken(token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        serviceUser.forgotPassword(email);
        return ResponseEntity.ok("Email de réinitialisation envoyé !");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String nouveauMotDePasse) {
        return ResponseEntity.ok(serviceUser.resetPassword(token, nouveauMotDePasse));
    }
}
