package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class BoiteClientResponseDTO {
    private Long id;
    private String nom;
    private int nbPorts;
    private int portsUtilises;
    private double latitude;
    private double longitude;
    private Long splitterId;
}
