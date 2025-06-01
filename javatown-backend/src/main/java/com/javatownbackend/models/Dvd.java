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
@DiscriminatorValue("DVD")
public class Dvd extends Document{

    private static final int DUREE_EMPRUNT_JOURS = 7;

    private String director;

    private int duree;

    public Dvd(String titre, int nombreExemplaires, int anneePublication, String director, int duree) {
        super(titre, nombreExemplaires, anneePublication);
        this.director = director;
        this.duree = duree;
    }

    @Override
    public int getDureeEmpruntJours() {
        return DUREE_EMPRUNT_JOURS;
    }
}