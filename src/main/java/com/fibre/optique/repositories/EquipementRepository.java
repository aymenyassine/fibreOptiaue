package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fibre.optique.models.Equipements;

@Repository
public interface EquipementRepository extends JpaRepository<Equipements, Long> {

}
