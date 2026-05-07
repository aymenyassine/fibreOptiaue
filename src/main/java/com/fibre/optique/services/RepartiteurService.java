package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Repartiteurs;

public interface RepartiteurService {
    public void addRepartiteur(Repartiteurs repartiteur);

    public List<Repartiteurs> getAllRepartiteurs();

    public Repartiteurs getRepartiteurById(Long id);

    public void updateRepartiteur(Long id, Repartiteurs repartiteur);

    public void deleteRepartiteur(Long id);

    public boolean verifierChamps(Repartiteurs repartiteur);
}
