package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Utilisateurs;
import com.fibre.optique.repositories.UserRepository;
import com.fibre.optique.exceptions.UtilisateurNotFoundException;
import com.fibre.optique.exceptions.UtilisateurInvalidException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void assignRoleToUser(Long userId, String roleName) {
        Utilisateurs utilisateur = getUtilisateurById(userId);
        utilisateur.setRole(com.fibre.optique.enums.Role.valueOf(roleName.toUpperCase()));
        userRepository.save(utilisateur);
    }

    @Override
    public void addUtilisateur(Utilisateurs utilisateur) {
        if(verifierChamps(utilisateur)){
            userRepository.save(utilisateur);
        }
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateurs getUtilisateurById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'id : " + id));
    }

    @Override
    public void updateUtilisateur(Long id, Utilisateurs utilisateur) {
        if(verifierChamps(utilisateur)){
            userRepository.save(utilisateur);
        }
    }

    @Override
    public void deleteUtilisateur(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Utilisateurs> findUtilisateursByRole(String roleName) {
        return userRepository.findByRole(com.fibre.optique.enums.Role.valueOf(roleName.toUpperCase()));
    }

    @Override
    public void register(Utilisateurs utilisateur) {
        if(verifierChamps(utilisateur)){
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            userRepository.save(utilisateur);
        }
    }

    @Override
    public Utilisateurs login(String username, String password) {
        Utilisateurs utilisateur = userRepository.findByEmail(username)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'email : " + username));
        if(!passwordEncoder.matches(password, utilisateur.getPassword())){
            throw new UtilisateurInvalidException("Mot de passe incorrect");
        }
        return utilisateur;
    }

    @Override
    public void logout() {
        // Logique de déconnexion (souvent gérée côté client avec JWT)
    }

    @Override
    public void forgetPassword(String email) {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'email : " + email));
        // Logique d'envoi d'email de réinitialisation à implémenter
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        Utilisateurs utilisateur = userRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'email : " + email));
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(utilisateur);
    }

    @Override
    public boolean verifierChamps(Utilisateurs utilisateur) {
        if(utilisateur.getNom() == null || utilisateur.getNom().isEmpty()){
            throw new UtilisateurInvalidException("Le nom de l'utilisateur ne peut pas être vide");
        }
        if(utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()){
            throw new UtilisateurInvalidException("L'email de l'utilisateur ne peut pas être vide");
        }
        if(utilisateur.getPassword() == null || utilisateur.getPassword().isEmpty()){
            throw new UtilisateurInvalidException("Le mot de passe de l'utilisateur ne peut pas être vide");
        }
        if(utilisateur.getRole() == null){
            throw new UtilisateurInvalidException("Le rôle de l'utilisateur ne peut pas être vide");
        }
        return true;
    }

}
