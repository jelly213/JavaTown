package com.javatownbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_document", discriminatorType = DiscriminatorType.STRING)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_document", insertable = false, updatable = false)
    private String type;

    private String titre;

    private int nombreExemplaires;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<EmpruntDetail> empruntDetails = new ArrayList<>();

    private int anneePublication;

    public Document(String titre, int nombreExemplaires, int anneePublication) {
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
        this.anneePublication = anneePublication;
    }

    public abstract int getDureeEmpruntJours();
}