package com.fibre.optique.dtos;

import com.fibre.optique.enums.Ratio;
import lombok.Data;

@Data
public class SplitterResponseDTO {
    private Long id;
    private Ratio ratio;
    private int nbSortie;
    private Long repartiteurId;
}
