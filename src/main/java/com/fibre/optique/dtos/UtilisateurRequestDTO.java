package com.fibre.optique.dtos;

import com.fibre.optique.enums.Role;
import lombok.Data;

@Data
public class UtilisateurRequestDTO {
    private String nom;
    private String email;
    private String password;
    private Role role;
}
