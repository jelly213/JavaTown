package com.javatownbackend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("prepose")
public class Prepose extends Utilisateur {

    public Prepose(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
}
