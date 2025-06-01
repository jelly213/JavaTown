package com.javatownbackend.persistence;

import com.javatownbackend.models.Emprunteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprunteurRepository extends JpaRepository<Emprunteur, Long> {
    boolean existsByEmail(String email);
}
