package com.fibre.optique.controllers;

import com.fibre.optique.dtos.EquipementRequestDTO;
import com.fibre.optique.dtos.EquipementResponseDTO;
import com.fibre.optique.mappers.EquipementMapper;
import com.fibre.optique.models.Equipements;
import com.fibre.optique.services.EquipementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final EquipementService equipementService;
    private final EquipementMapper equipementMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addEquipement(@RequestBody EquipementRequestDTO requestDTO) {
        Equipements equipement = equipementMapper.toEntity(requestDTO);
        equipementService.addEquipement(equipement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<EquipementResponseDTO>> getAllEquipements() {
        List<EquipementResponseDTO> equipements = equipementService.getAllEquipements()
                .stream()
                .map(equipementMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(equipements);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<EquipementResponseDTO> getEquipementById(@PathVariable Long id) {
        Equipements equipement = equipementService.getEquipementById(id);
        return ResponseEntity.ok(equipementMapper.toResponseDTO(equipement));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateEquipement(@PathVariable Long id, @RequestBody EquipementRequestDTO requestDTO) {
        Equipements equipement = equipementMapper.toEntity(requestDTO);
        equipementService.updateEquipement(id, equipement);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
