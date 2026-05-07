package com.fibre.optique.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Repartiteurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int nbPorts;

    @ManyToOne
    private Datacenters datacenter;

    @OneToMany(mappedBy = "repartiteur", cascade = CascadeType.ALL)
    private List<Equipements> equipements;

    @OneToMany(mappedBy = "repartiteur", cascade = CascadeType.ALL)
    private List<Splitters> splitters;
}