package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fibre.optique.models.Datacenters;

@Repository
public interface DatacenterRepository extends JpaRepository<Datacenters, Long> {

}
