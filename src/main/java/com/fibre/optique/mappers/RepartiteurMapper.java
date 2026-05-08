package com.fibre.optique.mappers;

import com.fibre.optique.dtos.RepartiteurRequestDTO;
import com.fibre.optique.dtos.RepartiteurResponseDTO;
import com.fibre.optique.models.Repartiteurs;
import com.fibre.optique.models.Datacenters;
import org.springframework.stereotype.Component;

@Component
public class RepartiteurMapper {
    public Repartiteurs toEntity(RepartiteurRequestDTO dto) {
        Repartiteurs entity = new Repartiteurs();
        entity.setNom(dto.getNom());
        entity.setNbPorts(dto.getNbPorts());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
        if(dto.getDatacenterId() != null){
            Datacenters d = new Datacenters();
            d.setId(dto.getDatacenterId());
            entity.setDatacenter(d);
        }
        return entity;
    }
    public RepartiteurResponseDTO toResponseDTO(Repartiteurs entity) {
        RepartiteurResponseDTO dto = new RepartiteurResponseDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setNbPorts(entity.getNbPorts());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        if(entity.getDatacenter() != null){
            dto.setDatacenterId(entity.getDatacenter().getId());
        }
        return dto;
    }
}
