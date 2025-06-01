package com.javatownbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmprunteursListDto {
    private List<EmprunteurDto> emprunteurs;
    private String messageErreur;

    public EmprunteursListDto(List<EmprunteurDto> emprunteurs) {
        this.emprunteurs = emprunteurs;
    }

    public EmprunteursListDto(String messageErreur) {
        this.messageErreur = messageErreur;
    }
}
