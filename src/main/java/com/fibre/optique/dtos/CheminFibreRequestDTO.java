package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class CheminFibreRequestDTO {
    private String source;
    private String destination;
    private double longueur;
    private String typeFibre;
    private String statut;
}
