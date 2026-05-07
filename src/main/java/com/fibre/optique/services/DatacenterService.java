package com.fibre.optique.services;

import java.util.List;

import com.fibre.optique.models.Datacenters;

public interface DatacenterService {

    public void addDatacenter(Datacenters datacenter);
    public List<Datacenters> getAllDatacenters();
    public Datacenters getDatacenterById(Long id);
    public void updateDatacenter(Long id ,Datacenters datacenter);
    public void deleteDatacenter(Long id);
    public boolean verifierChamps(Datacenters datacenter);
}
