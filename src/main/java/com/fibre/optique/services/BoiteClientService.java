package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.BoiteClients;

public interface BoiteClientService {
    public void addBoiteClient(BoiteClients boiteClient);

    public List<BoiteClients> getAllBoiteClients();

    public BoiteClients getBoiteClientById(Long id);

    public void updateBoiteClient(Long id, BoiteClients boiteClient);

    public void deleteBoiteClient(Long id);

    public boolean verifierChamps(BoiteClients boiteClient);
}
