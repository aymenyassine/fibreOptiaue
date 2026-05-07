package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Equipements;
import com.fibre.optique.repositories.EquipementRepository;
import com.fibre.optique.exceptions.EquipementNotFoundException;
import com.fibre.optique.exceptions.EquipementInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipementServiceImpl implements EquipementService {

    private EquipementRepository equipementRepository;

    @Override
    public void addEquipement(Equipements equipement) {
        if(verifierChamps(equipement)){
            equipementRepository.save(equipement);
        }
    }

    @Override
    public List<Equipements> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipements getEquipementById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement non trouvé avec l'id : " + id));
    }

    @Override
    public void updateEquipement(Long id, Equipements equipement) {
        if(verifierChamps(equipement)){
            equipementRepository.save(equipement);
        }
    }

    @Override
    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(Equipements equipement) {
        if(equipement.getIp() == null || equipement.getIp().isEmpty()){
            throw new EquipementInvalidException("L'IP de l'équipement ne peut pas être vide");
        }
        if(equipement.getStatus() == null){
            throw new RuntimeException("Le statut de l'équipement ne peut pas être vide");
        }
        if(equipement.getType() == null){
            throw new RuntimeException("Le type de l'équipement ne peut pas être vide");
        }
        if(equipement.getRepartiteur() == null){
            throw new RuntimeException("Le répartiteur de l'équipement ne peut pas être vide");
        }
        return true;
    }
}
