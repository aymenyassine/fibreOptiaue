package com.fibre.optique.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fibre.optique.models.Splitters;
import com.fibre.optique.repositories.SplitterRepository;
import com.fibre.optique.exceptions.SplitterNotFoundException;
import com.fibre.optique.exceptions.SplitterInvalidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SplitterServiceImpl implements SplitterService {

    private SplitterRepository splitterRepository;

    @Override
    public void addSplitter(Splitters splitter) {
        if(verifierChamps(splitter)){
            splitterRepository.save(splitter);
        }
    }

    @Override
    public List<Splitters> getAllSplitters() {
        return splitterRepository.findAll();
    }

    @Override
    public Splitters getSplitterById(Long id) {
        return splitterRepository.findById(id)
                .orElseThrow(() -> new SplitterNotFoundException("Splitter non trouvé avec l'id : " + id));
    }

    @Override
    public void updateSplitter(Long id, Splitters splitter) {
        if(verifierChamps(splitter)){
            splitterRepository.save(splitter);
        }
    }

    @Override
    public void deleteSplitter(Long id) {
        splitterRepository.deleteById(id);
    }

    @Override
    public boolean verifierChamps(Splitters splitter) {
        if(splitter.getRatio() == null){
            throw new SplitterInvalidException("Le ratio du splitter ne peut pas être vide");
        }
        if(splitter.getRepartiteur() == null){
            throw new SplitterInvalidException("Le répartiteur du splitter ne peut pas être vide");
        }
        if(splitter.getNbSortie() <= 0){
            throw new SplitterInvalidException("Le nombre de sorties du splitter doit être supérieur à 0");
        }
        return true;
    }
}
