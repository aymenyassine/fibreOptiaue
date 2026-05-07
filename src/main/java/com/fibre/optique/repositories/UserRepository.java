package com.fibre.optique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fibre.optique.models.Utilisateurs;

@Repository
public interface UserRepository extends JpaRepository<Utilisateurs, Long> {
    java.util.Optional<Utilisateurs> findByEmail(String email);
    java.util.List<Utilisateurs> findByRole(com.fibre.optique.enums.Role role);
}
