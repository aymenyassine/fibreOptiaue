package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Utilisateurs;

public interface UtilisateurService {
    public void assignRoleToUser(Long userId, String roleName);

    public void addUtilisateur(Utilisateurs utilisateur);

    public List<Utilisateurs> getAllUtilisateurs();

    public Utilisateurs getUtilisateurById(Long id);

    public void updateUtilisateur(Long id, Utilisateurs utilisateur);

    public void deleteUtilisateur(Long id);

    public List<Utilisateurs> findUtilisateursByRole(String roleName);

    public void register(Utilisateurs utilisateur);

    public Utilisateurs login(String username, String password);

    public void logout();

    public void forgetPassword(String email);

    public void resetPassword(String email, String newPassword);

    public boolean verifierChamps(Utilisateurs utilisateur);

}
