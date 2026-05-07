package com.fibre.optique.controllers;

import com.fibre.optique.dtos.UtilisateurResponseDTO;
import com.fibre.optique.enums.Role;
import com.fibre.optique.mappers.UtilisateurMapper;
import com.fibre.optique.models.Utilisateurs;
import com.fibre.optique.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @InjectMocks
    private UtilisateurController utilisateurController;



    private Utilisateurs utilisateur;
    private UtilisateurResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();

        utilisateur = new Utilisateurs();
        utilisateur.setId(1L);
        utilisateur.setNom("Alice");
        utilisateur.setRole(Role.ADMIN);

        responseDTO = new UtilisateurResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNom("Alice");
    }

    @Test
    void getAllUtilisateurs_returnsOkWithList() throws Exception {
        when(utilisateurService.getAllUtilisateurs()).thenReturn(Collections.singletonList(utilisateur));
        when(utilisateurMapper.toResponseDTO(any(Utilisateurs.class))).thenReturn(responseDTO);

        mockMvc.perform(get("/api/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Alice"));

        verify(utilisateurService, times(1)).getAllUtilisateurs();
    }

    @Test
    void assignRoleToUser_returnsOk() throws Exception {
        doNothing().when(utilisateurService).assignRoleToUser(1L, "THECNICIEN");

        mockMvc.perform(put("/api/utilisateurs/1/role")
                        .param("roleName", "THECNICIEN"))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).assignRoleToUser(1L, "THECNICIEN");
    }
}
