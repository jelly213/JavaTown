package com.backendjavatown.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "document_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private int numberOfCopies;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<LoanDetail> loanDetails = new ArrayList<>();

    public Document(String titre, int numberOfCopies) {
        this.titre = titre;
        this.numberOfCopies = numberOfCopies;
    }

    public abstract int getMaxLoanDays();
}
