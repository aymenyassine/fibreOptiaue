package com.fibre.optique.models;

import com.fibre.optique.enums.Ratio;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Splitters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Ratio ratio;

    private int nbSortie;

    @ManyToOne
    private Repartiteurs repartiteur;

    @OneToMany(mappedBy = "splitter", cascade = CascadeType.ALL)
    private List<BoiteClients> boitesClients;
}