package com.fibre.optique.mappers;
import com.fibre.optique.dtos.CheminFibreRequestDTO;
import com.fibre.optique.dtos.CheminFibreResponseDTO;
import com.fibre.optique.models.Cheminfibre;
import org.springframework.stereotype.Component;

@Component
public class CheminFibreMapper {
    public Cheminfibre toEntity(CheminFibreRequestDTO dto) {
        Cheminfibre entity = new Cheminfibre();
        entity.setSource(dto.getSource());
        entity.setDestination(dto.getDestination());
        entity.setLongueur(dto.getLongueur());
        entity.setTypeFibre(dto.getTypeFibre());
        entity.setStatut(dto.getStatut());
        return entity;
    }
    public CheminFibreResponseDTO toResponseDTO(Cheminfibre entity) {
        CheminFibreResponseDTO dto = new CheminFibreResponseDTO();
        dto.setId(entity.getId());
        dto.setSource(entity.getSource());
        dto.setDestination(entity.getDestination());
        dto.setLongueur(entity.getLongueur());
        dto.setTypeFibre(entity.getTypeFibre());
        dto.setStatut(entity.getStatut());
        return dto;
    }
}
