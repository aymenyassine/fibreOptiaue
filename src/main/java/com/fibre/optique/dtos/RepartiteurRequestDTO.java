package com.fibre.optique.dtos;

import lombok.Data;

@Data
public class RepartiteurRequestDTO {
    private String nom;
    private int nbPorts;
    private Double longitude;
    private Double latitude;
    private Long datacenterId;
}
