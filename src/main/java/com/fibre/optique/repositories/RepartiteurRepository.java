package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibre.optique.models.Repartiteurs;

@Repository
public interface RepartiteurRepository extends JpaRepository<Repartiteurs, Long> {

}
