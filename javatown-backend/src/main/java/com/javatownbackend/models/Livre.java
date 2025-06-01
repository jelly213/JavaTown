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
@DiscriminatorValue("Livre")
public class Livre extends Document {

    private static final int DUREE_EMPRUNT_JOURS = 21;

    private String auteur;

    private String editeur;

    private int nombrePages;

    public Livre(String titre, int nombreExemplaires, int anneePublication, String auteur, String editeur, int nombrePages) {
        super(titre, nombreExemplaires, anneePublication);
        this.auteur = auteur;
        this.editeur = editeur;
        this.nombrePages = nombrePages;
    }

    @Override
    public int getDureeEmpruntJours() {
        return DUREE_EMPRUNT_JOURS;
    }
}