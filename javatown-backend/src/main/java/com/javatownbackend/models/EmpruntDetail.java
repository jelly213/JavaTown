package com.javatownbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpruntDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    private LocalDate dateRetourPrevue;

    private LocalDate dateRetourActuelle;

    public EmpruntDetail(Document document, LocalDate dateRetourPrevue) {
        this.document = document;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Emprunteur getEmprunteur() {
        return this.emprunt.getEmprunteur();
    }
}
