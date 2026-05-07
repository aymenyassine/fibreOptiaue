package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Splitters;

public interface SplitterService {
    public void addSplitter(Splitters splitter);

    public List<Splitters> getAllSplitters();

    public Splitters getSplitterById(Long id);

    public void updateSplitter(Long id, Splitters splitter);

    public void deleteSplitter(Long id);

    public boolean verifierChamps(Splitters splitter);
}
