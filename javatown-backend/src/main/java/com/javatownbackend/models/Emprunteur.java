package com.javatownbackend.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("emprunteur")
public class Emprunteur extends Utilisateur {

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Amende> amendes = new ArrayList<>();

    public Emprunteur(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    public void addEmprunts(Emprunt emprunt) {
        emprunts.add(emprunt);
    }

    public void addAmende(Amende amende) {
        amendes.add(amende);
    }
}