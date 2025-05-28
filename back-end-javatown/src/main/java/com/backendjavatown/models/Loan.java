package com.backendjavatown.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanDetail> loanDetail = new ArrayList<>();

    private LocalDate loanDate;
    public Loan(Customer customer, LocalDate loanDate) {
        this.customer = customer;
        this.loanDate = loanDate;
    }

}
