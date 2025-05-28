package com.backendjavatown.models;

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
public class Fine {

    private static final double LATE_RETURN_FEE = 0.25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double amount;

    private LocalDate creationDate;

    private boolean status;

    public Fine(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
        this.status = false;
    }

    public static double getLATE_RETURN_FEE() {
        return LATE_RETURN_FEE;
    }
}

