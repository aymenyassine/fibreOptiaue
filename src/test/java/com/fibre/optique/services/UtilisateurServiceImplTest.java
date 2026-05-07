package com.fibre.optique.services;

import com.fibre.optique.enums.Role;
import com.fibre.optique.exceptions.UtilisateurInvalidException;
import com.fibre.optique.exceptions.UtilisateurNotFoundException;
import com.fibre.optique.models.Utilisateurs;
import com.fibre.optique.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    private Utilisateurs validUser;

    @BeforeEach
    void setUp() {
        validUser = new Utilisateurs();
        validUser.setId(1L);
        validUser.setNom("John Doe");
        validUser.setEmail("john@example.com");
        validUser.setPassword("password123");
        validUser.setRole(Role.ADMIN);
    }

    @Test
    void register_validUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(Utilisateurs.class))).thenReturn(validUser);

        utilisateurService.register(validUser);

        assertEquals("encodedPassword", validUser.getPassword());
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepository, times(1)).save(validUser);
    }

    @Test
    void register_invalidUser() {
        validUser.setEmail(""); // Invalid
        assertThrows(UtilisateurInvalidException.class, () -> utilisateurService.register(validUser));
        verify(userRepository, never()).save(any(Utilisateurs.class));
    }

    @Test
    void login_success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(validUser));
        when(passwordEncoder.matches("password123", validUser.getPassword())).thenReturn(true);

        Utilisateurs result = utilisateurService.login("john@example.com", "password123");

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void login_wrongPassword() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(validUser));
        when(passwordEncoder.matches("wrongpass", validUser.getPassword())).thenReturn(false);

        assertThrows(UtilisateurInvalidException.class, () -> utilisateurService.login("john@example.com", "wrongpass"));
    }

    @Test
    void login_userNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThrows(UtilisateurNotFoundException.class, () -> utilisateurService.login("notfound@example.com", "anypass"));
    }

    @Test
    void assignRoleToUser_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(validUser));
        when(userRepository.save(any(Utilisateurs.class))).thenReturn(validUser);

        utilisateurService.assignRoleToUser(1L, "THECNICIEN");

        assertEquals(Role.THECNICIEN, validUser.getRole());
        verify(userRepository, times(1)).save(validUser);
    }
}
