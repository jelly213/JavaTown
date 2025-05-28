package com.backendjavatown.persistence;

import com.backendjavatown.models.Cd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdRepository extends JpaRepository<Cd,Long> {
}
