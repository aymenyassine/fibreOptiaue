package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class BoiteClientRequestDTO {
    private String nom;
    private int nbPorts;
    private int portsUtilises;
    private double latitude;
    private double longitude;
    private Long splitterId;
}
