package com.backendjavatown.models;

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
@DiscriminatorValue("Book")
public class Book extends Document {

    private static final int MAX_LOAN_DAYS = 21;

    private String author;

    private String editor;

    private int numberOfPages;

    private int publicationYear;


    public Book(String title, int numberOfCopies, int publicationYear, String author, String editor, int numberOfPages) {
        super(title, numberOfCopies );
        this.author = author;
        this.editor = editor;
        this.numberOfPages = numberOfPages;
        this.publicationYear = publicationYear;
    }

    @Override
    public int getMaxLoanDays() {
        return MAX_LOAN_DAYS;
    }
}
