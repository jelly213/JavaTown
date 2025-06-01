package com.javatownbackend.persistence;

import com.javatownbackend.models.Cd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdRepository extends JpaRepository<Cd, Long> {
}
