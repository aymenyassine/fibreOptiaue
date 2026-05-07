package com.fibre.optique.dtos;
import lombok.Data;
@Data
public class DatacenterRequestDTO {
    private String nom;
    private Integer capacite;
    private Double latitude;
    private Double longitude;
}
