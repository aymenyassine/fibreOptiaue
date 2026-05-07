package com.fibre.optique.controllers;

import com.fibre.optique.dtos.DatacenterRequestDTO;
import com.fibre.optique.dtos.DatacenterResponseDTO;
import com.fibre.optique.mappers.DatacenterMapper;
import com.fibre.optique.models.Datacenters;
import com.fibre.optique.services.DatacenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/datacenters")
@RequiredArgsConstructor
public class DatacenterController {

    private final DatacenterService datacenterService;
    private final DatacenterMapper datacenterMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addDatacenter(@RequestBody DatacenterRequestDTO requestDTO) {
        Datacenters datacenter = datacenterMapper.toEntity(requestDTO);
        datacenterService.addDatacenter(datacenter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<List<DatacenterResponseDTO>> getAllDatacenters() {
        List<DatacenterResponseDTO> datacenters = datacenterService.getAllDatacenters()
                .stream()
                .map(datacenterMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(datacenters);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'THECNICIEN')")
    public ResponseEntity<DatacenterResponseDTO> getDatacenterById(@PathVariable Long id) {
        Datacenters datacenter = datacenterService.getDatacenterById(id);
        return ResponseEntity.ok(datacenterMapper.toResponseDTO(datacenter));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateDatacenter(@PathVariable Long id, @RequestBody DatacenterRequestDTO requestDTO) {
        Datacenters datacenter = datacenterMapper.toEntity(requestDTO);
        datacenterService.updateDatacenter(id, datacenter);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDatacenter(@PathVariable Long id) {
        datacenterService.deleteDatacenter(id);
        return ResponseEntity.noContent().build();
    }
}
