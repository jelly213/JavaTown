package com.backendjavatown.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DocumentDto {
    private Long id;
    private String title;
    private int copies;
    private int maxLoanDays;
}