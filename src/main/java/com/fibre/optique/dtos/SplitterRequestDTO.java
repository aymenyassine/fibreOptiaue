package com.fibre.optique.dtos;

import com.fibre.optique.enums.Ratio;
import lombok.Data;

@Data
public class SplitterRequestDTO {
    private Ratio ratio;
    private int nbSortie;
    private Long repartiteurId;
}
