package org.esprit.application_nutrition.Services;

import lombok.RequiredArgsConstructor;
import org.esprit.application_nutrition.Entites.*;
import org.esprit.application_nutrition.Repo.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceUser {

    private final UserRepo userRepository;
    private final TokenRepo tokenRepository;
    private final ResetTokenRepo resetTokenRepository;
    private final JavaMailSender mailSender;

    // ===================== INSCRIPTION =====================
    public User inscrire(User user) {
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Ce compte existe déjà avec cet email !");
        }

        user.setEnabled(false); // désactiver jusqu'à confirmation
        userRepository.save(user);

        // Créer un token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusHours(24))
                .user(user)
                .build();
        tokenRepository.save(confirmationToken);

        // Envoyer l'email
        String link = "http://localhost:4200/confirm?token=" + token;
        sendEmail(user.getEmail(), "Confirme ton inscription",
                "Clique sur ce lien pour activer ton compte : " + link);

        return user;
    }


    // ===================== CONNEXION =====================
    public User seConnecter(String email, String motDePasse) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getMotDePasse().equals(motDePasse))
                .orElse(null); // connexion directe si mdp correct
    }

    // ===================== CONFIRMATION TOKEN =====================
    public String confirmerToken(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token introuvable !"));

        if (confirmationToken.isConfirmed()) return "Email déjà confirmé";
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) return "Token expiré";

        confirmationToken.setConfirmed(true);
        tokenRepository.save(confirmationToken);

        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return "Email confirmé avec succès !";
    }

    // ===================== MOT DE PASSE OUBLIE =====================
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Email introuvable !"));

        String token = UUID.randomUUID().toString();
        ResetPasswordToken resetToken = ResetPasswordToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusHours(1))
                .used(false)
                .user(user)
                .build();
        resetTokenRepository.save(resetToken);

        String link = "http://localhost:4200/reset-password?token=" + token;
        sendEmail(user.getEmail(), "Réinitialise ton mot de passe",
                "Clique sur ce lien pour réinitialiser ton mot de passe : " + link);
    }

    public String resetPassword(String token, String nouveauMotDePasse) {
        ResetPasswordToken resetToken = resetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token introuvable !"));

        if (resetToken.isUsed()) return "Token déjà utilisé";
        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) return "Token expiré";

        User user = resetToken.getUser();
        user.setMotDePasse(nouveauMotDePasse);
        userRepository.save(user);

        resetToken.setUsed(true);
        resetTokenRepository.save(resetToken);

        return "Mot de passe réinitialisé avec succès !";
    }

    // ===================== ENVOI EMAIL =====================
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
