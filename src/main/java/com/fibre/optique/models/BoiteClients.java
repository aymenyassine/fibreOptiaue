package com.fibre.optique.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoiteClients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int nbPorts;
    private int portsUtilises;

    private double latitude;
    private double longitude;

    @ManyToOne
    private Splitters splitter;
}