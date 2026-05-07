package com.fibre.optique.mappers;
import com.fibre.optique.dtos.DatacenterRequestDTO;
import com.fibre.optique.dtos.DatacenterResponseDTO;
import com.fibre.optique.models.Datacenters;
import org.springframework.stereotype.Component;

@Component
public class DatacenterMapper {
    public Datacenters toEntity(DatacenterRequestDTO dto) {
        Datacenters entity = new Datacenters();
        entity.setNom(dto.getNom());
        entity.setCapacite(dto.getCapacite());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        return entity;
    }
    public DatacenterResponseDTO toResponseDTO(Datacenters entity) {
        DatacenterResponseDTO dto = new DatacenterResponseDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setCapacite(entity.getCapacite());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;
    }
}
