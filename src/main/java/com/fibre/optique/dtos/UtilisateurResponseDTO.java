package com.fibre.optique.dtos;

import com.fibre.optique.enums.Role;
import lombok.Data;

@Data
public class UtilisateurResponseDTO {
    private Long id;
    private String nom;
    private String email;
    private Role role;
}
