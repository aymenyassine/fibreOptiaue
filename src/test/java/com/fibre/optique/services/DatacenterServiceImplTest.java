package com.fibre.optique.services;

import com.fibre.optique.exceptions.DatacenterInvalidException;
import com.fibre.optique.exceptions.DatacenterNotFoundException;
import com.fibre.optique.models.Datacenters;
import com.fibre.optique.models.Repartiteurs;
import com.fibre.optique.repositories.DatacenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatacenterServiceImplTest {

    @Mock
    private DatacenterRepository datacenterRepository;

    @InjectMocks
    private DatacenterServiceImpl datacenterService;

    private Datacenters validDatacenter;

    @BeforeEach
    void setUp() {
        validDatacenter = new Datacenters();
        validDatacenter.setId(1L);
        validDatacenter.setNom("DC1");
        validDatacenter.setLatitude(33.5);
        validDatacenter.setLongitude(-7.6);
        validDatacenter.setCapacite(100);
        validDatacenter.setRepartiteurs(Collections.singletonList(new Repartiteurs()));
    }

    @Test
    void addDatacenter_valid() {
        when(datacenterRepository.save(any(Datacenters.class))).thenReturn(validDatacenter);

        datacenterService.addDatacenter(validDatacenter);

        verify(datacenterRepository, times(1)).save(validDatacenter);
    }

    @Test
    void addDatacenter_invalid() {
        Datacenters invalidDC = new Datacenters();
        invalidDC.setNom(""); // Invalid, will throw exception

        assertThrows(DatacenterInvalidException.class, () -> datacenterService.addDatacenter(invalidDC));
        verify(datacenterRepository, never()).save(any(Datacenters.class));
    }

    @Test
    void getAllDatacenters() {
        when(datacenterRepository.findAll()).thenReturn(Collections.singletonList(validDatacenter));

        List<Datacenters> result = datacenterService.getAllDatacenters();

        assertEquals(1, result.size());
        assertEquals("DC1", result.get(0).getNom());
        verify(datacenterRepository, times(1)).findAll();
    }

    @Test
    void getDatacenterById_found() {
        when(datacenterRepository.findById(1L)).thenReturn(Optional.of(validDatacenter));

        Datacenters result = datacenterService.getDatacenterById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(datacenterRepository, times(1)).findById(1L);
    }

    @Test
    void getDatacenterById_notFound() {
        when(datacenterRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(DatacenterNotFoundException.class, () -> datacenterService.getDatacenterById(99L));
        verify(datacenterRepository, times(1)).findById(99L);
    }

    @Test
    void updateDatacenter() {
        when(datacenterRepository.save(validDatacenter)).thenReturn(validDatacenter);

        datacenterService.updateDatacenter(1L, validDatacenter);

        verify(datacenterRepository, times(1)).save(validDatacenter);
    }

    @Test
    void deleteDatacenter() {
        doNothing().when(datacenterRepository).deleteById(1L);

        datacenterService.deleteDatacenter(1L);

        verify(datacenterRepository, times(1)).deleteById(1L);
    }
}
