package com.javatownbackend.persistence;

import com.javatownbackend.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByTitreContainingIgnoreCase(String titre);

    @Query("select d from Document d where " +
            "(type(d) = Livre and treat (d as Livre).auteur = :auteur) or " +
            "(type(d) = Livre and treat (d as Livre).editeur = :auteur) or " +
            "(type(d) = Cd and treat (d as Cd).artiste = :auteur) OR " +
            "(type(d) = Dvd and treat (d as Dvd).director = :auteur)")
    List<Document> findByAuteur(@Param("auteur") String auteur);

    List<Document> findByAnneePublication(long anneePublication);

    @Query("select d from Document d where d.type = :documentType")
    List<Document> findByTypeDocument(@Param("documentType") String documentType);
}
