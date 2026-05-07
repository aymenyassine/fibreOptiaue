package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class DatacenterResponseDTO {
    private Long id;
    private String nom;
    private Integer capacite;
    private Double latitude;
    private Double longitude;
}
