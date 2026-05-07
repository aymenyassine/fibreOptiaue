package com.fibre.optique.controllers;

import com.fibre.optique.dtos.UtilisateurRequestDTO;
import com.fibre.optique.dtos.UtilisateurResponseDTO;
import com.fibre.optique.mappers.UtilisateurMapper;
import com.fibre.optique.models.Utilisateurs;
import com.fibre.optique.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

    @PostMapping
    public ResponseEntity<Void> addUtilisateur(@RequestBody UtilisateurRequestDTO requestDTO) {
        Utilisateurs utilisateur = utilisateurMapper.toEntity(requestDTO);
        utilisateurService.addUtilisateur(utilisateur);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> getAllUtilisateurs() {
        List<UtilisateurResponseDTO> utilisateurs = utilisateurService.getAllUtilisateurs()
                .stream()
                .map(utilisateurMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(@PathVariable Long id) {
        Utilisateurs utilisateur = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(utilisateurMapper.toResponseDTO(utilisateur));
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<UtilisateurResponseDTO>> getUtilisateursByRole(@PathVariable String roleName) {
        List<UtilisateurResponseDTO> utilisateurs = utilisateurService.findUtilisateursByRole(roleName)
                .stream()
                .map(utilisateurMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurRequestDTO requestDTO) {
        Utilisateurs utilisateur = utilisateurMapper.toEntity(requestDTO);
        utilisateurService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Void> assignRoleToUser(@PathVariable Long id, @RequestParam String roleName) {
        utilisateurService.assignRoleToUser(id, roleName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
