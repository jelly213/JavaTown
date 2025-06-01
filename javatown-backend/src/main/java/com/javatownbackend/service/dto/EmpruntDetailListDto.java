package com.javatownbackend.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmpruntDetailListDto {
    private List<EmpruntDetailDto> emprunts;
    private String messageErreur;

    public EmpruntDetailListDto(List<EmpruntDetailDto> emprunts) {
        this.emprunts = emprunts;
    }

    public EmpruntDetailListDto(String messageErreur) {
        this.messageErreur = messageErreur;
    }
}
