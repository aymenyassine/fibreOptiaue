package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fibre.optique.models.Splitters;

@Repository
public interface SplitterRepository extends JpaRepository<Splitters, Long> {

}
