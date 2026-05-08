package com.fibre.optique.dtos;

import com.fibre.optique.enums.Status;
import com.fibre.optique.enums.Type;
import lombok.Data;

@Data
public class EquipementResponseDTO {
    private Long id;
    private String nom;
    private String modele;
    private String numSerie;
    private String ip;
    private Status status;
    private Type type;
    private Long repartiteurId;
}
