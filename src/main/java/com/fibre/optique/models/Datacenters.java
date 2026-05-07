package com.fibre.optique.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Datacenters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double latitude;
    private Double longitude;
    private Integer capacite;

    @OneToMany(mappedBy = "datacenter", cascade = CascadeType.ALL)
    private List<Repartiteurs> repartiteurs;
}