package com.backendjavatown.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("customer")
public class Customer extends Users {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Fine> fines = new ArrayList<>();

    public Customer(String name, String email, String phoneNumber, String password) {
        super(name, email, phoneNumber, password);
    }

    public void addEmprunts(Loan loan) {
        loans.add(loan);
    }

    public void addAmende(Fine fine) {
        fines.add(fine);
    }
}
