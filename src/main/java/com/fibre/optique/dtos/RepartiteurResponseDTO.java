package com.fibre.optique.dtos;

import lombok.Data;

@Data
public class RepartiteurResponseDTO {
    private Long id;
    private String nom;
    private int nbPorts;
    private Long datacenterId;
}
