package com.backendjavatown.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("employee")
@NoArgsConstructor
public class Employee extends Users {

    public Employee(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

}
