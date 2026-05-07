package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Cheminfibre;

public interface CheminFibreService {
    public void addCheminFibre(Cheminfibre cheminFibre);
    public List<Cheminfibre> getAllCheminFibre();
    public Cheminfibre getCheminFibreById(Long id);
    public void updateCheminFibre(Long id, Cheminfibre cheminFibre);
    public void deleteCheminFibre(Long id);
    public boolean verifierChamps(Cheminfibre cheminFibre);
}
