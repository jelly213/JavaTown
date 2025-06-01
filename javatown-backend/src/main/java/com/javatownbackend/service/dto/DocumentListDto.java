package com.javatownbackend.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocumentListDto {
    private List<DocumentDto> documents;
    private String messageErreur;

    public DocumentListDto(List<DocumentDto> documents, String messageErreur) {
        this.documents = documents;
        this.messageErreur = messageErreur;
    }

    public DocumentListDto(List<DocumentDto> documents) {
        this.documents = documents;
    }

    public DocumentListDto(String messageErreur) {
        this.messageErreur = messageErreur;
    }

}
