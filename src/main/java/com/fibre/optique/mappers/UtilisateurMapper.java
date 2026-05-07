package com.fibre.optique.mappers;

import com.fibre.optique.dtos.UtilisateurRequestDTO;
import com.fibre.optique.dtos.UtilisateurResponseDTO;
import com.fibre.optique.models.Utilisateurs;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public Utilisateurs toEntity(UtilisateurRequestDTO dto) {
        Utilisateurs entity = new Utilisateurs();
        entity.setNom(dto.getNom());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }
    public UtilisateurResponseDTO toResponseDTO(Utilisateurs entity) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        return dto;
    }
}
