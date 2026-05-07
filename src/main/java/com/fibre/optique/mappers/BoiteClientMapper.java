package com.fibre.optique.mappers;
import com.fibre.optique.dtos.BoiteClientRequestDTO;
import com.fibre.optique.dtos.BoiteClientResponseDTO;
import com.fibre.optique.models.BoiteClients;
import com.fibre.optique.models.Splitters;
import org.springframework.stereotype.Component;

@Component
public class BoiteClientMapper {
    public BoiteClients toEntity(BoiteClientRequestDTO dto) {
        BoiteClients entity = new BoiteClients();
        entity.setNom(dto.getNom());
        entity.setNbPorts(dto.getNbPorts());
        entity.setPortsUtilises(dto.getPortsUtilises());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        if(dto.getSplitterId() != null) {
            Splitters s = new Splitters();
            s.setId(dto.getSplitterId());
            entity.setSplitter(s);
        }
        return entity;
    }

    public BoiteClientResponseDTO toResponseDTO(BoiteClients entity) {
        BoiteClientResponseDTO dto = new BoiteClientResponseDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setNbPorts(entity.getNbPorts());
        dto.setPortsUtilises(entity.getPortsUtilises());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        if(entity.getSplitter() != null) {
            dto.setSplitterId(entity.getSplitter().getId());
        }
        return dto;
    }
}
