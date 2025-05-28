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
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    public LoanDetail(Document document, LocalDate expectedReturnDate) {
        this.document = document;
        this.expectedReturnDate = expectedReturnDate;
    }

    public Customer getCustomer() {
        return this.loan.getCustomer();
    }

}
