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
@NoArgsConstructor
@AllArgsConstructor
public class Amende {

    private static final double FRAI_DE_RETARD = 0.25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Emprunteur emprunteur;

    private double montant;

    private LocalDate dateCreation;

    private boolean status;

    public Amende(Emprunteur emprunteur, double montant) {
        this.emprunteur = emprunteur;
        this.montant = montant;
        this.status = false;
    }

    public static double getFraiDeRetard(){
        return FRAI_DE_RETARD;
    }
}
