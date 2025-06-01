package com.javatownbackend.persistence;

import com.javatownbackend.models.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvdRepository extends JpaRepository<Dvd, Long> {
}
