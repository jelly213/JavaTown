package com.javatownbackend.persistence;


import com.javatownbackend.models.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
