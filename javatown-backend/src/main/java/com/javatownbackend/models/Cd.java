package com.javatownbackend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CD")
public class Cd extends Document {

    private static final int DUREE_EMPRUNT_JOURS = 14;

    private String artiste;

    private int duree;

    private String genre;

    public Cd(String titre, int nombreExemplaires, int anneePublication, String artiste, int duree, String genre) {
        super(titre, nombreExemplaires, anneePublication);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }

    @Override
    public int getDureeEmpruntJours() {
        return DUREE_EMPRUNT_JOURS;
    }
}