package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.BoiteClients;
import com.fibre.optique.repositories.BoiteClientRepository;
import com.fibre.optique.exceptions.BoiteClientNotFoundException;
import com.fibre.optique.exceptions.BoiteClientInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoiteClientServiceImp implements BoiteClientService{

    private BoiteClientRepository boiteClientRepository;

    @Override
    public void addBoiteClient(BoiteClients boiteClient) {
        if(verifierChamps(boiteClient)){
            boiteClientRepository.save(boiteClient);
        }
    }

    @Override
    public List<BoiteClients> getAllBoiteClients() {
        return boiteClientRepository.findAll();
    }

    @Override
    public BoiteClients getBoiteClientById(Long id) {
        return boiteClientRepository.findById(id)
                .orElseThrow(() -> new BoiteClientNotFoundException("Boite client non trouvée avec l'id : " + id));
    }

    @Override
    public void updateBoiteClient(Long id, BoiteClients boiteClient) {
        if(verifierChamps(boiteClient)){
            boiteClientRepository.save(boiteClient);
        }
    }

    @Override
    public void deleteBoiteClient(Long id) {
        boiteClientRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(BoiteClients boiteClient) {
        if(boiteClient.getNom() == null || boiteClient.getNom().isEmpty()){
            throw new BoiteClientInvalidException("Le nom de la boite client ne peut pas être vide");
        }
        if(boiteClient.getNbPorts() <= 0){
            throw new RuntimeException("Le nombre de ports de la boite client doit être supérieur à 0");
        }
        if(boiteClient.getPortsUtilises() < 0){
            throw new RuntimeException("Le nombre de ports utilisés de la boite client ne peut pas être négatif");
        }
        if(boiteClient.getSplitter() == null){
            throw new RuntimeException("Le splitter de la boite client ne peut pas être vide");
        }
        return true;
    }
}
