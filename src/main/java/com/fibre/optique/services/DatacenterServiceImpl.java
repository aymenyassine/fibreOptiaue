package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Datacenters;
import com.fibre.optique.repositories.DatacenterRepository;
import com.fibre.optique.exceptions.DatacenterNotFoundException;
import com.fibre.optique.exceptions.DatacenterInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DatacenterServiceImpl implements DatacenterService {

    private DatacenterRepository datacenterRepository;

    @Override
    public void addDatacenter(Datacenters datacenter) {
        if(verifierChamps(datacenter)){
            datacenterRepository.save(datacenter);
        }
    }

    @Override
    public List<Datacenters> getAllDatacenters() {
        return datacenterRepository.findAll();
    }

    @Override
    public Datacenters getDatacenterById(Long id) {
        return datacenterRepository.findById(id)
                .orElseThrow(() -> new DatacenterNotFoundException("Datacenter non trouvé avec l'id : " + id));
    }

    @Override
    public void updateDatacenter(Long id, Datacenters datacenter) {
       if(verifierChamps(datacenter)){
           datacenterRepository.save(datacenter);
       }
    }

    @Override
    public void deleteDatacenter(Long id) {
        datacenterRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(Datacenters datacenter) {
        if(datacenter.getNom().isEmpty()){
            throw new DatacenterInvalidException("Le nom du datacenter ne peut pas être vide");
        }
        if(datacenter.getLatitude()== null){
            throw new DatacenterInvalidException("La latitude du datacenter ne peut pas être vide");
        }
        if(datacenter.getLongitude()== null){
            throw new DatacenterInvalidException("La longitude du datacenter ne peut pas être vide");
        }
        if(datacenter.getCapacite() == null){
            throw new DatacenterInvalidException("La capacité du datacenter ne peut pas être vide");
        }
        return true;
    }
    
}