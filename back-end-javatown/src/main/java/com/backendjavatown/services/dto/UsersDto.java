package com.backendjavatown.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}