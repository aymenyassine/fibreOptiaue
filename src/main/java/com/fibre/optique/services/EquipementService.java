package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Equipements;

public interface EquipementService {
    public void addEquipement(Equipements equipement);

    public List<Equipements> getAllEquipements();

    public Equipements getEquipementById(Long id);

    public void updateEquipement(Long id, Equipements equipement);

    public void deleteEquipement(Long id);

    public boolean verifierChamps(Equipements equipement);
}
