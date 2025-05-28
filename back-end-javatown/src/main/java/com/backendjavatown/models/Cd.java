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
@DiscriminatorValue("Cd")
public class Cd extends Document{
    private static final int MAX_LOAN_DAYS = 14;

    private String artist;
    private int duration;
    private String genre;

    public Cd(String title, int copies, String artist, int duration, String genre) {
        super(title, copies);
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    @Override
    public int getMaxLoanDays() {
        return MAX_LOAN_DAYS;
    }
}
