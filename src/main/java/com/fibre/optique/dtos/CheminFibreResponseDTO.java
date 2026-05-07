package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class CheminFibreResponseDTO {
    private Long id;
    private String source;
    private String destination;
    private double longueur;
    private String typeFibre;
    private String statut;
}
