package com.fibre.optique.mappers;

import com.fibre.optique.dtos.EquipementRequestDTO;
import com.fibre.optique.dtos.EquipementResponseDTO;
import com.fibre.optique.models.Equipements;
import com.fibre.optique.models.Repartiteurs;
import org.springframework.stereotype.Component;

@Component
public class EquipementMapper {
    public Equipements toEntity(EquipementRequestDTO dto) {
        Equipements entity = new Equipements();
        entity.setIp(dto.getIp());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        if(dto.getRepartiteurId() != null){
            Repartiteurs r = new Repartiteurs();
            r.setId(dto.getRepartiteurId());
            entity.setRepartiteur(r);
        }
        return entity;
    }
    public EquipementResponseDTO toResponseDTO(Equipements entity) {
        EquipementResponseDTO dto = new EquipementResponseDTO();
        dto.setId(entity.getId());
        dto.setIp(entity.getIp());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        if(entity.getRepartiteur() != null){
            dto.setRepartiteurId(entity.getRepartiteur().getId());
        }
        return dto;
    }
}
