package com.fibre.optique.controllers;

import com.fibre.optique.dtos.SplitterRequestDTO;
import com.fibre.optique.dtos.SplitterResponseDTO;
import com.fibre.optique.mappers.SplitterMapper;
import com.fibre.optique.models.Splitters;
import com.fibre.optique.services.SplitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/splitters")
@RequiredArgsConstructor
public class SplitterController {

    private final SplitterService splitterService;
    private final SplitterMapper splitterMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addSplitter(@RequestBody SplitterRequestDTO requestDTO) {
        Splitters splitter = splitterMapper.toEntity(requestDTO);
        splitterService.addSplitter(splitter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<SplitterResponseDTO>> getAllSplitters() {
        List<SplitterResponseDTO> splitters = splitterService.getAllSplitters()
                .stream()
                .map(splitterMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(splitters);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<SplitterResponseDTO> getSplitterById(@PathVariable Long id) {
        Splitters splitter = splitterService.getSplitterById(id);
        return ResponseEntity.ok(splitterMapper.toResponseDTO(splitter));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateSplitter(@PathVariable Long id, @RequestBody SplitterRequestDTO requestDTO) {
        Splitters splitter = splitterMapper.toEntity(requestDTO);
        splitterService.updateSplitter(id, splitter);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSplitter(@PathVariable Long id) {
        splitterService.deleteSplitter(id);
        return ResponseEntity.noContent().build();
    }
}
