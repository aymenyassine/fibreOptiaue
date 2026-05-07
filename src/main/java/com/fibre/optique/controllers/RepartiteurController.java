package com.fibre.optique.controllers;

import com.fibre.optique.dtos.RepartiteurRequestDTO;
import com.fibre.optique.dtos.RepartiteurResponseDTO;
import com.fibre.optique.mappers.RepartiteurMapper;
import com.fibre.optique.models.Repartiteurs;
import com.fibre.optique.services.RepartiteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/repartiteurs")
@RequiredArgsConstructor
public class RepartiteurController {

    private final RepartiteurService repartiteurService;
    private final RepartiteurMapper repartiteurMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addRepartiteur(@RequestBody RepartiteurRequestDTO requestDTO) {
        Repartiteurs repartiteur = repartiteurMapper.toEntity(requestDTO);
        repartiteurService.addRepartiteur(repartiteur);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<RepartiteurResponseDTO>> getAllRepartiteurs() {
        List<RepartiteurResponseDTO> repartiteurs = repartiteurService.getAllRepartiteurs()
                .stream()
                .map(repartiteurMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(repartiteurs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<RepartiteurResponseDTO> getRepartiteurById(@PathVariable Long id) {
        Repartiteurs repartiteur = repartiteurService.getRepartiteurById(id);
        return ResponseEntity.ok(repartiteurMapper.toResponseDTO(repartiteur));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateRepartiteur(@PathVariable Long id, @RequestBody RepartiteurRequestDTO requestDTO) {
        Repartiteurs repartiteur = repartiteurMapper.toEntity(requestDTO);
        repartiteurService.updateRepartiteur(id, repartiteur);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRepartiteur(@PathVariable Long id) {
        repartiteurService.deleteRepartiteur(id);
        return ResponseEntity.noContent().build();
    }
}
