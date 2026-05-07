package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fibre.optique.models.Cheminfibre;

@Repository
public interface CheminFibreRepository extends JpaRepository<Cheminfibre, Long> {

}
