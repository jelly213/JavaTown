package com.javatownbackend.persistence;

import com.javatownbackend.models.EmpruntDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpruntDetailRepository extends JpaRepository <EmpruntDetail, Long> {
    @Query("select count(*) from EmpruntDetail ed where ed.document.id =:idDocument and ed.dateRetourActuelle =null")
    int getNombreEmpruntsParDocument(@Param("idDocument") long idDocument);
}
