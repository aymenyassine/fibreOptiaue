package com.fibre.optique.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fibre.optique.dtos.DatacenterRequestDTO;
import com.fibre.optique.dtos.DatacenterResponseDTO;
import com.fibre.optique.mappers.DatacenterMapper;
import com.fibre.optique.models.Datacenters;
import com.fibre.optique.services.DatacenterService;
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
class DatacenterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DatacenterService datacenterService;

    @Mock
    private DatacenterMapper datacenterMapper;

    @InjectMocks
    private DatacenterController datacenterController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private DatacenterRequestDTO requestDTO;
    private Datacenters datacenter;
    private DatacenterResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(datacenterController).build();

        requestDTO = new DatacenterRequestDTO();
        requestDTO.setNom("DC1");

        datacenter = new Datacenters();
        datacenter.setId(1L);
        datacenter.setNom("DC1");

        responseDTO = new DatacenterResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNom("DC1");
    }

    @Test
    void addDatacenter_returns201() throws Exception {
        when(datacenterMapper.toEntity(any(DatacenterRequestDTO.class))).thenReturn(datacenter);
        doNothing().when(datacenterService).addDatacenter(any(Datacenters.class));

        mockMvc.perform(post("/api/datacenters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());

        verify(datacenterService, times(1)).addDatacenter(any(Datacenters.class));
    }

    @Test
    void getAllDatacenters_returnsOkWithList() throws Exception {
        when(datacenterService.getAllDatacenters()).thenReturn(Collections.singletonList(datacenter));
        when(datacenterMapper.toResponseDTO(any(Datacenters.class))).thenReturn(responseDTO);

        mockMvc.perform(get("/api/datacenters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("DC1"));

        verify(datacenterService, times(1)).getAllDatacenters();
    }

    @Test
    void deleteDatacenter_returns204() throws Exception {
        doNothing().when(datacenterService).deleteDatacenter(1L);

        mockMvc.perform(delete("/api/datacenters/1"))
                .andExpect(status().isNoContent());

        verify(datacenterService, times(1)).deleteDatacenter(1L);
    }
}
