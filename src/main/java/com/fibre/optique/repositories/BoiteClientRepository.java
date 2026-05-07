package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibre.optique.models.BoiteClients;

@Repository
public interface BoiteClientRepository extends JpaRepository<BoiteClients, Long> {

}
