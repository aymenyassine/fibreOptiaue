package com.fibre.optique.models;

import com.fibre.optique.enums.Status;
import com.fibre.optique.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    private Repartiteurs repartiteur;
}