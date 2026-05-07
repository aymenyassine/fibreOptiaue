package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Cheminfibre;
import com.fibre.optique.repositories.CheminFibreRepository;
import com.fibre.optique.exceptions.CheminFibreNotFoundException;
import com.fibre.optique.exceptions.CheminFibreInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheminFibreServiceImpl implements CheminFibreService {

    private CheminFibreRepository cheminFibreRepository;

    @Override
    public void addCheminFibre(Cheminfibre cheminFibre) {
        if(verifierChamps(cheminFibre)){
            cheminFibreRepository.save(cheminFibre);
        }
    }

    @Override
    public List<Cheminfibre> getAllCheminFibre() {
        return cheminFibreRepository.findAll();
    }

    @Override
    public Cheminfibre getCheminFibreById(Long id) {
        return cheminFibreRepository.findById(id)
                .orElseThrow(() -> new CheminFibreNotFoundException("Chemin de fibre non trouvé avec l'id : " + id));
    }

    @Override
    public void updateCheminFibre(Long id, Cheminfibre cheminFibre) {
        if(verifierChamps(cheminFibre)){
            cheminFibreRepository.save(cheminFibre);
        }
    }

    @Override
    public void deleteCheminFibre(Long id) {
        cheminFibreRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(Cheminfibre cheminFibre) {
        if(cheminFibre.getSource() == null || cheminFibre.getSource().isEmpty()){
            throw new CheminFibreInvalidException("La source du chemin de fibre ne peut pas être vide");
        }
        if(cheminFibre.getDestination() == null || cheminFibre.getDestination().isEmpty()){
            throw new CheminFibreInvalidException("La destination du chemin de fibre ne peut pas être vide");
        }
        if(cheminFibre.getTypeFibre() == null || cheminFibre.getTypeFibre().isEmpty()){
            throw new RuntimeException("Le type de fibre du chemin de fibre ne peut pas être vide");
        }
        if(cheminFibre.getStatut() == null || cheminFibre.getStatut().isEmpty()){
            throw new RuntimeException("Le statut du chemin de fibre ne peut pas être vide");
        }
        if(cheminFibre.getLongueur() <= 0){
            throw new RuntimeException("La longueur du chemin de fibre doit être supérieure à 0");
        }
        return true;
    }
}
