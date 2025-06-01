package com.javatownbackend.persistence;

import com.javatownbackend.models.Amende;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmendeRepository extends JpaRepository<Amende, Long> {
}
