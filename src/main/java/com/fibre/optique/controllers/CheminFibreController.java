package com.fibre.optique.controllers;

import com.fibre.optique.dtos.CheminFibreRequestDTO;
import com.fibre.optique.dtos.CheminFibreResponseDTO;
import com.fibre.optique.mappers.CheminFibreMapper;
import com.fibre.optique.models.Cheminfibre;
import com.fibre.optique.services.CheminFibreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chemin-fibres")
@RequiredArgsConstructor
public class CheminFibreController {

    private final CheminFibreService cheminFibreService;
    private final CheminFibreMapper cheminFibreMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addCheminFibre(@RequestBody CheminFibreRequestDTO requestDTO) {
        Cheminfibre cheminFibre = cheminFibreMapper.toEntity(requestDTO);
        cheminFibreService.addCheminFibre(cheminFibre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<CheminFibreResponseDTO>> getAllCheminFibres() {
        List<CheminFibreResponseDTO> chemins = cheminFibreService.getAllCheminFibre()
                .stream()
                .map(cheminFibreMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chemins);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<CheminFibreResponseDTO> getCheminFibreById(@PathVariable Long id) {
        Cheminfibre cheminFibre = cheminFibreService.getCheminFibreById(id);
        return ResponseEntity.ok(cheminFibreMapper.toResponseDTO(cheminFibre));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateCheminFibre(@PathVariable Long id, @RequestBody CheminFibreRequestDTO requestDTO) {
        Cheminfibre cheminFibre = cheminFibreMapper.toEntity(requestDTO);
        cheminFibreService.updateCheminFibre(id, cheminFibre);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCheminFibre(@PathVariable Long id) {
        cheminFibreService.deleteCheminFibre(id);
        return ResponseEntity.noContent().build();
    }
}
