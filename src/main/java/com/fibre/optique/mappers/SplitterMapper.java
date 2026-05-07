package com.fibre.optique.mappers;

import com.fibre.optique.dtos.SplitterRequestDTO;
import com.fibre.optique.dtos.SplitterResponseDTO;
import com.fibre.optique.models.Splitters;
import com.fibre.optique.models.Repartiteurs;
import org.springframework.stereotype.Component;

@Component
public class SplitterMapper {
    public Splitters toEntity(SplitterRequestDTO dto) {
        Splitters entity = new Splitters();
        entity.setRatio(dto.getRatio());
        entity.setNbSortie(dto.getNbSortie());
        if(dto.getRepartiteurId() != null){
            Repartiteurs r = new Repartiteurs();
            r.setId(dto.getRepartiteurId());
            entity.setRepartiteur(r);
        }
        return entity;
    }
    public SplitterResponseDTO toResponseDTO(Splitters entity) {
        SplitterResponseDTO dto = new SplitterResponseDTO();
        dto.setId(entity.getId());
        dto.setRatio(entity.getRatio());
        dto.setNbSortie(entity.getNbSortie());
        if(entity.getRepartiteur() != null){
            dto.setRepartiteurId(entity.getRepartiteur().getId());
        }
        return dto;
    }
}
