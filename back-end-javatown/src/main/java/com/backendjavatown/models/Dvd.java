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
@DiscriminatorValue("Dvd")
public class Dvd extends Document {

    private static final int MAX_LOAN_DAYS = 7;

    private String director;
    private int duration;

    public Dvd(String title, int copies, String director, int duration) {
        super(title, copies);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public int getMaxLoanDays() {
        return MAX_LOAN_DAYS;
    }
}
