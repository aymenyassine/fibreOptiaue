package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Repartiteurs;
import com.fibre.optique.repositories.RepartiteurRepository;
import com.fibre.optique.exceptions.RepartiteurNotFoundException;
import com.fibre.optique.exceptions.RepartiteurInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepartiteurServiceImpl implements RepartiteurService {

    private RepartiteurRepository repartiteurRepository;

    @Override
    public void addRepartiteur(Repartiteurs repartiteur) {
        if(verifierChamps(repartiteur)){
            repartiteurRepository.save(repartiteur);
        }
    }

    @Override
    public List<Repartiteurs> getAllRepartiteurs() {
        return repartiteurRepository.findAll();
    }

    @Override
    public Repartiteurs getRepartiteurById(Long id) {
        return repartiteurRepository.findById(id)
                .orElseThrow(() -> new RepartiteurNotFoundException("Répartiteur non trouvé avec l'id : " + id));
    }

    @Override
    public void updateRepartiteur(Long id, Repartiteurs repartiteur) {
        if(verifierChamps(repartiteur)){
            repartiteurRepository.save(repartiteur);
        }
    }

    @Override
    public void deleteRepartiteur(Long id) {
        repartiteurRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(Repartiteurs repartiteur) {
        if(repartiteur.getNom() == null || repartiteur.getNom().isEmpty()){
            throw new RepartiteurInvalidException("Le nom du répartiteur ne peut pas être vide");
        }
        if(repartiteur.getDatacenter() == null){
            throw new RepartiteurInvalidException("Le datacenter du répartiteur ne peut pas être vide");
        }
        if(repartiteur.getNbPorts() <= 0){
            throw new RepartiteurInvalidException("Le nombre de ports du répartiteur doit être supérieur à 0");
        }
        return true;
    }
}
