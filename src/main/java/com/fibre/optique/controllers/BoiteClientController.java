package com.fibre.optique.controllers;

import com.fibre.optique.dtos.BoiteClientRequestDTO;
import com.fibre.optique.dtos.BoiteClientResponseDTO;
import com.fibre.optique.mappers.BoiteClientMapper;
import com.fibre.optique.models.BoiteClients;
import com.fibre.optique.services.BoiteClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boite-clients")
@RequiredArgsConstructor
public class BoiteClientController {

    private final BoiteClientService boiteClientService;
    private final BoiteClientMapper boiteClientMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addBoiteClient(@RequestBody BoiteClientRequestDTO requestDTO) {
        BoiteClients boiteClient = boiteClientMapper.toEntity(requestDTO);
        boiteClientService.addBoiteClient(boiteClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<BoiteClientResponseDTO>> getAllBoiteClients() {
        List<BoiteClientResponseDTO> boites = boiteClientService.getAllBoiteClients()
                .stream()
                .map(boiteClientMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(boites);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<BoiteClientResponseDTO> getBoiteClientById(@PathVariable Long id) {
        BoiteClients boiteClient = boiteClientService.getBoiteClientById(id);
        return ResponseEntity.ok(boiteClientMapper.toResponseDTO(boiteClient));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateBoiteClient(@PathVariable Long id, @RequestBody BoiteClientRequestDTO requestDTO) {
        BoiteClients boiteClient = boiteClientMapper.toEntity(requestDTO);
        boiteClientService.updateBoiteClient(id, boiteClient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBoiteClient(@PathVariable Long id) {
        boiteClientService.deleteBoiteClient(id);
        return ResponseEntity.noContent().build();
    }
}
