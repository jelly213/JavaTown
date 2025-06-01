package com.javatownbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private long id;
    private String titre;
    private int nombreExemplaires;
    private int anneePublication;
    private String type;

    private String messageErreur;

    public DocumentDto(String messageErreur) {
        this.messageErreur = messageErreur;
    }
}
