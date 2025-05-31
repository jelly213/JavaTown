package com.backendjavatown.persistence;

import com.backendjavatown.models.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvdRepository extends JpaRepository<Dvd, Long> {
}
